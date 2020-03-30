package ru.mycity.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CoreConfig {

    @Value("${jira.api.url}")
    private String jiraApiUrl;

    @Value("${jira.api.cookie}")
    private String jiraApiCookie;

    public String getJiraApiUrl() {
        return jiraApiUrl;
    }

    public String getJiraApiCookie() {
        return jiraApiCookie;
    }
}
