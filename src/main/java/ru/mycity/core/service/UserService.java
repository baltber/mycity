package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.ResultDto;
import ru.mycity.core.controller.dto.user.*;
import ru.mycity.core.service.dao.IUserDao;
import ru.mycity.core.service.dao.impl.UserDaoImpl;
import ru.mycity.core.service.dao.model.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    public AuthUserResponseDto auth(AuthUserRequestDto requestDto){
         Optional <UserDto> user = userDao.getUserByUserName(requestDto.getUserName())
                .stream()
                .map(User::toDto).findFirst();

         if (user.isPresent()){
             return createAuthResonseOk(user.get());
         } else {
             return createAuthResonseNotFound();
         }
    }

    public AddUserResponseDto add(AddUserRequestDto requestDto){
        UserDto userDto = requestDto.getUserDto();
        long userId =  userDao.addNewUser(
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getRole());
        return userId != 0 ? createResponseOk() : createResponseError();
    }

    private AuthUserResponseDto createAuthResonseOk(UserDto userDto){
        AuthUserResponseDto responseDto=  new AuthUserResponseDto();
        responseDto.setUserDto(userDto);
        responseDto.setResultDto(new ResultDto("200", "OK"));
        return responseDto;
    }

    private AuthUserResponseDto createAuthResonseNotFound(){
        AuthUserResponseDto responseDto=  new AuthUserResponseDto();
        responseDto.setResultDto(new ResultDto("404", "NOT_FOUND"));
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
}
