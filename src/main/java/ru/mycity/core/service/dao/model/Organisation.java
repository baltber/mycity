package ru.mycity.core.service.dao.model;

public class Organisation {

    private long organisationId;
    private String organisationGuid;
    private String config;
    private String name;

    public Organisation(long organisationId, String organisationGuid, String config, String name) {
        this.organisationId = organisationId;
        this.organisationGuid = organisationGuid;
        this.config = config;
        this.name = name;
    }

    public long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
    }

    public String getOrganisationGuid() {
        return organisationGuid;
    }

    public void setOrganisationGuid(String organisationGuid) {
        this.organisationGuid = organisationGuid;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

