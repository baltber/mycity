package ru.mycity.core.service.rest.dto.mapper;

import ru.mycity.core.controller.dto.order.FullOrderDto;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.service.rest.dto.*;
import ru.mycity.core.utils.JsonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class JiraToPlainMapper {


    private JiraSearchResponse jiraResponse;

    public JiraToPlainMapper(JiraSearchResponse jiraResponse) {
        this.jiraResponse = jiraResponse;
    }

    public FullOrderDto toPlainDto(){
        FullOrderDto fullOrderDto = new FullOrderDto(jiraResponse.getStartAt(),
                jiraResponse.getMaxResults(),
                jiraResponse.getTotal());
        fullOrderDto.setOrders(toOrderListDto());

        return fullOrderDto;
    }

    public List<OrderRequestDto> toOrderListDto(){

        return jiraResponse.getIssues().stream()
                .map(this :: orderRequestDto)
                .collect(Collectors.toList());
    }

    private OrderRequestDto orderRequestDto(Issue issue){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setSummary(issue.getFields().getSummary());
        orderRequestDto.setName(issue.getFields().getCustomerName());
        orderRequestDto.setAddress(issue.getFields().getAddress());
        orderRequestDto.setPhone(issue.getFields().getPhone());
        orderRequestDto.setEmail(issue.getFields().getEmail());
        orderRequestDto.setComment(getCommentIfExist(issue));
        orderRequestDto.setOrderList(orderList(issue));
        return orderRequestDto;
    }

    private String getCommentIfExist(Issue issue){
        Comment comment = issue.getFields().getComment();
        if(comment != null){
            Content content = comment.getContent().get(0);
            if(content != null && content.getContent() != null && content.getContent().size()>0){
                return content.getContent().get(0).getText();
            }
        }
        return null;
    }


    private OrderList orderList(Issue issue){

        Description description = issue.getFields().getDescription();
        if(description != null){
            Content content = description.getContent().get(0);
            if(content != null && content.getContent() != null && content.getContent().size()>0){
                return new JsonUtils<OrderList>().readValue(content.getContent().get(0).getText(), OrderList.class);
            }
        }
        return null;
    }
}
