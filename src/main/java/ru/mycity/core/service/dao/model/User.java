package ru.mycity.core.service.dao.model;

import ru.mycity.core.controller.dto.user.AuthUserResponseDto;
import ru.mycity.core.controller.dto.user.UserDto;

public class User {

    private String userName;
    private String password;
    private String role;

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserDto toDto(){
        UserDto dto = new UserDto();
        dto.setUserName(userName);
        dto.setPassword(password);
        dto.setRole(role);
        return dto;
    }
}
