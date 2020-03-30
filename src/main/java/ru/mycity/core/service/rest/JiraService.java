package ru.mycity.core.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mycity.core.config.CoreConfig;
import ru.mycity.core.service.rest.dto.JiraOrderRequest;
import ru.mycity.core.service.rest.dto.JiraOrderResponse;
import ru.mycity.core.utils.JsonUtils;

@Service
public class JiraService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CoreConfig config;

    public String addOrder(JiraOrderRequest orderRequest){

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", config.getJiraApiCookie());
        HttpEntity<JiraOrderRequest> request = new HttpEntity<>(orderRequest, headers);
        System.out.println(new JsonUtils<JiraOrderRequest>().convertToJson(orderRequest));
       return restTemplate.postForObject(config.getJiraApiUrl() + "/issue", request, String.class);
    }
}
