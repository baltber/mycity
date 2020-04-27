package ru.mycity.core.service.dao;

import ru.mycity.core.controller.exception.NotFoundException;
import ru.mycity.core.service.dao.model.Organisation;

public interface IOrganisationDao {
    long save(Organisation user);
    long getIdByGuid(String guid) throws NotFoundException;
    long getProjectIdByGuid(String guid) throws NotFoundException;
    String getConfigByGuid(String guid);
}
