package ru.mycity.core.service.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigFilter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("filter_id")
    private String filterId;
    @JsonProperty("container_id")
    private String containerId;

    public ConfigFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }
}
