package ru.mycity.core.service.dao;

import ru.mycity.core.service.dao.model.Organisation;

public interface IOrganisationDao {
    long save(Organisation user);
    long getIdByGuid(String guid);
    String getConfigByGuid(String guid);
}
