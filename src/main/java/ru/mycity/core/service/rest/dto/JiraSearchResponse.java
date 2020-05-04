package ru.mycity.core.service.rest.dto;

import java.util.List;

public class JiraSearchReaponse {
    int startAt;
    int maxResults;
    int total;
    List<Issue> issues;
}


