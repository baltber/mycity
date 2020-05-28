package ru.mycity.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.ResultDto;
import ru.mycity.core.controller.dto.user.*;
import ru.mycity.core.controller.exception.BadRequestException;
import ru.mycity.core.controller.exception.NotFoundException;
import ru.mycity.core.service.dao.IOrganisationDao;
import ru.mycity.core.service.dao.IUserDao;
import ru.mycity.core.service.dao.model.User;
import ru.mycity.core.utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IOrganisationDao organisationDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthUserResponseDto auth(AuthUserRequestDto requestDto){
        try{
            final Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(requestDto.getLogin(), requestDto.getPassword())); //

            // if authentication was successful we will update the security context and redirect to the page requested first
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return getUserByLogin(requestDto.getLogin());
        } catch (AuthenticationException ex){
            log.error(ex.getMessage(), ex);
            return createAuthResponseUnauthorized();
        }
    }

    public AuthUserResponseDto adminAuth(AuthUserRequestDto requestDto){
        try{
            return getUserByLogin(requestDto.getLogin());
        } catch (AuthenticationException ex){
            log.error(ex.getMessage(), ex);
            return createAuthResponseUnauthorized();
        }
    }

    public AuthUserResponseDto getUserByLogin(String login){
         Optional <UserDto> user = userDao.getUserByLogin(login)
                .stream()
                .map(User::toDto).findFirst();

         if (user.isPresent()){
             return createAuthResponseOk(user.get());
         } else {
             return createAuthResponseNotFound();
         }
    }

    public List<UserDto> getUserList(UserDto userDto){

        return userDao.getUserList(userDto)
                .stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }



    public AddUserResponseDto add(AddUserRequestDto requestDto) throws BadRequestException, NotFoundException {
        UserDto userDto = requestDto.getUserDto();
        //Закодируем пароль
        String pass = new BCryptPasswordEncoder().encode(userDto.getPassword());
        userDto.setPassword(pass);
        userDto.setUserId(userDto.getUserId() != null ? userDto.getUserId() : Utils.getUUID());
        userDto.setRole("user");
        return addUser(userDto);
    }

    public AddUserResponseDto addAdmin(AddUserRequestDto requestDto) throws BadRequestException, NotFoundException {
        UserDto userDto = requestDto.getUserDto();
        userDto.setUserId(userDto.getUserId() != null ? userDto.getUserId() : Utils.getUUID());
        return addUser(userDto);
    }

    public AddUserResponseDto addUser(UserDto userDto) throws BadRequestException, NotFoundException {
        User user = userDto.toEntity();
        if(userDto.getOrganisationGuid() != null){
            long orgId = organisationDao.getIdByGuid(userDto.getOrganisationGuid());
            user.setOrganisationId(orgId);
        }
        try{
            long id =  userDao.save(user);
            if (id != 0){
                return createResponseOk();
            } else {
                throw new BadRequestException("Ошибка добавления нового пользователя");
            }
        } catch (Exception ex){
            throw new BadRequestException("Неизвестная ошибка");
        }
    }

    public AddUserResponseDto connectToOrganisation(AddUserRequestDto requestDto) throws NotFoundException {
        Optional <User> user = userDao.getUserByLogin(requestDto.getUserDto().getLogin())
                .stream().findFirst();

        if (user.isPresent() && requestDto.getUserDto().getOrganisationGuid() != null){
            long orgId = organisationDao.getIdByGuid(requestDto.getUserDto().getOrganisationGuid());
            userDao.updateOrgId(user.get().getUserId(), orgId);
            return createResponseOk();
        } else {
            return createAddResponseNotFound();
        }
    }

    private AuthUserResponseDto createAuthResponseOk(UserDto userDto){
        AuthUserResponseDto responseDto =  new AuthUserResponseDto();
        responseDto.setUserDto(userDto);
        responseDto.setResultDto(new ResultDto("200", "OK"));
        return responseDto;
    }

    private AuthUserResponseDto createAuthResponseNotFound(){
        AuthUserResponseDto responseDto=  new AuthUserResponseDto();
        responseDto.setResultDto(new ResultDto("404", "NOT_FOUND"));
        return responseDto;
    }

    private AuthUserResponseDto createAuthResponseUnauthorized(){
        AuthUserResponseDto responseDto=  new AuthUserResponseDto();
        responseDto.setResultDto(new ResultDto("401", "Unauthorized"));
        return responseDto;
    }


    private AddUserResponseDto createResponseOk(){
        ResultDto resultDto = new ResultDto();
        resultDto.setStatusCode("200");
        resultDto.setMessage("OK");
        AddUserResponseDto responseDto = new AddUserResponseDto();
        responseDto.setResultDto(resultDto);
        return responseDto;
    }

    private AddUserResponseDto createResponseError(){
        ResultDto resultDto = new ResultDto();
        resultDto.setStatusCode("500");
        resultDto.setMessage("ERROR");
        AddUserResponseDto responseDto = new AddUserResponseDto();
        responseDto.setResultDto(resultDto);
        return responseDto;
    }

    private AddUserResponseDto createAddResponseNotFound(){
        ResultDto resultDto = new ResultDto();
        resultDto.setStatusCode("404");
        resultDto.setMessage("NOT_FOUND");
        AddUserResponseDto responseDto = new AddUserResponseDto();
        responseDto.setResultDto(resultDto);
        return responseDto;
    }
}
