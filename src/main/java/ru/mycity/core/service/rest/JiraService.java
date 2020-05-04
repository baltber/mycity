package ru.mycity.core.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mycity.core.config.CoreConfig;
import ru.mycity.core.service.rest.dto.JiraOrderRequest;
import ru.mycity.core.service.rest.dto.JiraSearchResponse;
import ru.mycity.core.utils.JsonUtils;

@Service
public class JiraService {
    private Logger log = LoggerFactory.getLogger(JiraService.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CoreConfig config;

    public String addOrder(JiraOrderRequest orderRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", config.getJiraApiCookie());
        HttpEntity<JiraOrderRequest> request = new HttpEntity<>(orderRequest, headers);
        log.info("HTTP Request to Jira API: " + new JsonUtils<JiraOrderRequest>().convertToJson(orderRequest).toString());
       return restTemplate.postForObject(config.getJiraApiUrl() + "/issue", request, String.class);
    }

    public JiraSearchResponse getListOrder(String jqlRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", config.getJiraApiCookie());
        HttpEntity<String> request = new HttpEntity<>(jqlRequest, headers);
        log.info("HTTP Request to Jira API: " + request);
        return restTemplate.postForObject(config.getJiraApiUrl() + "/search", request, JiraSearchResponse.class);
    }
}
