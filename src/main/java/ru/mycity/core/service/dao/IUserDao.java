package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.User;

import java.util.List;

public interface IUserDao {
    List<User> getUserByUserName(String userName);
    long addNewUser(String userName, String password, String role);
}
