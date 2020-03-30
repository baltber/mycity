package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.order.OrderDto;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.dto.order.OrderResponseDto;
import ru.mycity.core.service.rest.JiraService;
import ru.mycity.core.service.rest.dto.*;
import ru.mycity.core.utils.JsonUtils;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private JiraService jiraService;
    @Autowired
    private JsonUtils<List<OrderDto>> jsonUtils;

    public String add(OrderRequestDto requestDto){

        return jiraService.addOrder(toJiraRequest(requestDto));

    }

    private JiraOrderRequest toJiraRequest(OrderRequestDto requestDto){

        Fields fields = new Fields();
        Issuetype issuetype = new Issuetype("10011");
        fields.setIssuetype(issuetype);
        Project project = new Project("10001");
        fields.setProject(project);

        Description description = new Description();
        description.setVersion(1);
        description.setType("doc");
        Content content = new Content();
        content.setType("paragraph");
        OrderContent orderContent = new OrderContent("text",
                jsonUtils.convertToJson(requestDto.getOrder()).toString());
        content.setContent(Collections.singletonList(orderContent));
        fields.setCustomerName(requestDto.getName());
        fields.setSummary("Доставка еды REST");
        Address address = new Address(requestDto.getAddress(), "10004");
        fields.setAddress(address);
        fields.setFlat(requestDto.getFlat());
        description.setContent(Collections.singletonList(content));

        fields.setDescription(description);

        return new JiraOrderRequest(fields);
    }

    private OrderResponseDto createOkResult(){
        return new OrderResponseDto();
    }
}
