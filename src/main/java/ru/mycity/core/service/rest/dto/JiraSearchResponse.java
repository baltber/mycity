package ru.mycity.core.service.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class JiraSearchResponse {
    @JsonProperty("startAt")
    private int startAt;
    @JsonProperty("maxResults")
    private int maxResults;
    @JsonProperty("total")
    private int total;
    @JsonProperty("issues")
    private List<Issue> issues;

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}


