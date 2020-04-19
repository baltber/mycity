package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.User;

import java.util.List;

public interface IOrganisationDao {
    long save(User user);
    long getIdByGuid(String guid);
    String getConfigByGuid(String guid);
}
