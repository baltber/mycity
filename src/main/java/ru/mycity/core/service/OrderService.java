package ru.mycity.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mycity.core.controller.dto.order.FullOrderDto;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.controller.dto.order.OrderRequestDto;
import ru.mycity.core.controller.dto.order.OrderResponseDto;
import ru.mycity.core.controller.exception.NotFoundException;
import ru.mycity.core.service.dao.IOrganisationDao;
import ru.mycity.core.service.dao.model.ConfigFilter;
import ru.mycity.core.service.rest.JiraService;
import ru.mycity.core.service.rest.dto.*;
import ru.mycity.core.service.rest.dto.mapper.JiraToPlainMapper;
import ru.mycity.core.utils.JsonUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Service
public class OrderService {

    private final static String MAX_COUNT="10";
    @Autowired
    private JiraService jiraService;
    @Autowired
    private JsonUtils<OrderList> jsonUtils;
    @Autowired
    private IOrganisationDao organisationDao;

    public String add(OrderRequestDto requestDto) throws NotFoundException {

        return jiraService.addOrder(toJiraRequest(requestDto));

    }

    public FullOrderDto getListOrder(String guid, String orderState, int start, int size){
        //Вытащим конфиг
        String config = organisationDao.getConfigByGuid(guid);
        String request = createOrderListRequest(config, orderState, start, size);
        JiraToPlainMapper mapper = new JiraToPlainMapper(jiraService.getListOrder(request));
        return mapper.toPlainDto();
    }

    private String createOrderListRequest(String config, String orderState, int start, int size){
        Map<String, ConfigFilter> filterMap = null;
        try {
            filterMap = new JsonUtils<ConfigFilter>().readMap(config, ConfigFilter.class);
            return createOrderJqlRequest(filterMap.get(orderState).getFilterId(), start, size);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    private String createOrderJqlRequest(String filterId, int start, int size){

        return "{\"jql\": \"filter=" +
                filterId +
                " ORDER BY created DESC\"," +
                "\"fields\": [\"summary\", \"status\", \"created\", \"issuetype\", \"description\"," +
                " \"customfield_10049\", \"customfield_10054\",\n" +
                "                \"customfield_10055\", \"customfield_10056\", \"customfield_10057\",\"status\"]," +
                "\"startAt\":" + start + "," +
                "\"maxResults\" : " + size + "}";
    }

    private JiraOrderRequest toJiraRequest(OrderRequestDto requestDto) throws NotFoundException {

        Fields fields = new Fields();
        Issuetype issuetype = new Issuetype("10011");
        fields.setIssuetype(issuetype);
        Project project = new Project(String.valueOf(organisationDao.getProjectIdByGuid(requestDto.getOrganisationGuid())));
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
