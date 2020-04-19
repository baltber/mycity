package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.dto.order.OrderResponseDto;
import ru.mycity.core.service.rest.JiraService;
import ru.mycity.core.service.rest.dto.*;
import ru.mycity.core.utils.JsonUtils;

import java.util.Collections;

@Service
public class OrderService {

    @Autowired
    private JiraService jiraService;
    @Autowired
    private JsonUtils<OrderList> jsonUtils;

    public String add(OrderRequestDto requestDto){

        return jiraService.addOrder(toJiraRequest(requestDto));

    }

    private JiraOrderRequest toJiraRequest(OrderRequestDto requestDto){

        Fields fields = new Fields();
        Issuetype issuetype = new Issuetype("10011");
        fields.setIssuetype(issuetype);
        Project project = new Project("10001");
        fields.setProject(project);

        Content descriptionContent = createContent(jsonUtils.convertToJson(requestDto.getOrderList()).toString());
        Description description = new Description(1, "doc", Collections.singletonList(descriptionContent));
        fields.setDescription(description);
        if(requestDto.getComment() != null){
            Content commentContent = createContent( requestDto.getComment());
            Comment comment = new Comment(1, "doc", Collections.singletonList(commentContent));
            fields.setComment(comment);
        }


        fields.setCustomerName(requestDto.getName());
        fields.setSummary(requestDto.getSummary());
        fields.setAddress(requestDto.getAddress());
        fields.setEmail(requestDto.getEmail());
        fields.setPhone(requestDto.getPhone());
        fields.setFlat(requestDto.getFlat());



        return new JiraOrderRequest(fields);
    }

    private OrderResponseDto createOkResult(){
        return new OrderResponseDto();
    }

    private Content createContent(String text){
        Content content = new Content();
        content.setType("paragraph");
        OrderContent orderContentComment = new OrderContent("text",
                text);
        content.setContent(Collections.singletonList(orderContentComment));
        return content;
    }

}
