package ru.mycity.core.service.dao;

import ru.mycity.core.controller.dto.user.UserDto;
import ru.mycity.core.service.dao.model.User;

import java.util.List;

public interface IUserDao {
    List<User> getUserByLogin(String userName);
    List<User> getUserList(UserDto userDto);
    long save(User user);
    void updateOrgId(long userId, long orgId);
}
