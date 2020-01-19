package ru.mycity.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.user.AuthUserRequestDto;
import ru.mycity.core.controller.dto.user.AuthUserResponseDto;
import ru.mycity.core.controller.dto.user.UserDto;
import ru.mycity.core.service.UserService;


@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AuthUserResponseDto responseDto = userService.getUserByLogin(s);
        if (responseDto !=null){
            return toUserDetails(responseDto);
        } else {
            throw new UsernameNotFoundException("User not Found");
        }
    }

    private UserDetails toUserDetails(AuthUserResponseDto responseDto) {
        UserDto userDto = responseDto.getUserDto();
        return User.withUsername(userDto.getLogin())
                .password(userDto.getPassword())
                .roles(userDto.getRole()).build();
    }
}
