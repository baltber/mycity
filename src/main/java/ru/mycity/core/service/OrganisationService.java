package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.service.dao.IOrganisationDao;

@Service
public class OrganisationService {

    @Autowired
    private IOrganisationDao organisationDao;

    public void addNewOrganisation(){
    //TODO
    }

    public String getOrganisationConfig(String guid){
        return organisationDao.getConfigByGuid(guid);
    }


}
