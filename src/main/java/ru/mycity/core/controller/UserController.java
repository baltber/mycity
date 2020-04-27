package ru.mycity.core.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.mycity.core.controller.dto.user.*;
import ru.mycity.core.controller.exception.BadRequestException;
import ru.mycity.core.controller.exception.NotFoundException;
import ru.mycity.core.service.UserService;

import java.util.List;

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

    @RequestMapping(path = "/admin/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Авторизация")
    public AuthUserResponseDto adminAuth(@RequestBody AuthUserRequestDto requestDto) {
        return service.adminAuth(requestDto);
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление нового пользователя")
    public AddUserResponseDto addUser(@RequestBody AddUserRequestDto requestDto) throws BadRequestException, NotFoundException {
        return service.add(requestDto);
    }

    @RequestMapping(path = "/connect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Добавление пользователя в организацию")
    public AddUserResponseDto addUserToOrganisation(@RequestBody AddUserRequestDto requestDto) throws NotFoundException {
        return service.connectToOrganisation(requestDto);
    }

    @RequestMapping(path = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    @ApiOperation(value = "Получить список пользователей")
    public List<UserDto> getUserList(@RequestBody UserDto userDto) {
        return service.getUserList(userDto);
    }

}

