package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.User;

import java.util.List;

public interface IUserDao {
    List<User> getUserByLogin(String userName);
    long save(User user);
}
