package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.user.AddUserRequestDto;
import ru.mycity.core.controller.dto.user.AddUserResponseDto;
import ru.mycity.core.controller.dto.user.AuthUserRequestDto;
import ru.mycity.core.controller.dto.user.AuthUserResponseDto;
import ru.mycity.core.service.UserService;

@Component
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;


    @RequestMapping(path = "/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Авторизация")
    public AuthUserResponseDto auth(@RequestBody AuthUserRequestDto requestDto) {
        return service.auth(requestDto);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление нового пользователя")
    public AddUserResponseDto addUser(@RequestBody AddUserRequestDto requestDto) {
        return service.add(requestDto);
    }
}
