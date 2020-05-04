package ru.mycity.core;

import org.junit.Assert;
import org.junit.Test;
import ru.mycity.core.controller.dto.order.OrderList;
import ru.mycity.core.service.dao.model.ConfigFilter;
import ru.mycity.core.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class JsonUtilTest {

    @Test
    public void test() throws IOException {
        String json = "{\"new\": {\"name\": \"new\",\"filter_id\": 10066,\"container_id\": \"rec172290814\"},\"process\": {\"name\": \"process\",\"filter_id\": 10063,\"container_id\": \"rec172269115\"},\"delivery\": {\"name\": \"delivery\",\"filter_id\": 10064,\"container_id\": \"rec172573790\"}}";

        Map<String, ConfigFilter> filter = new JsonUtils<ConfigFilter>().readMap(json, ConfigFilter.class);
        ConfigFilter configFilter = filter.get("new");
        Assert.assertEquals("10066", configFilter.getFilterId());
    }

    @Test
    public void testOrderList() {
        String json = "{\"delivery\":\"В пределах МКАД\",\"deliveryPrice\":0,\"totalPrice\":1790,\"order\":[{\"name\":\"Валерияна\",\"quantity\":\"1\",\"price\":\"580\",\"amount\":\"580\"},{\"name\":\"Каротосалата\",\"quantity\":\"1\",\"price\":\"390\",\"amount\":\"390\"},{\"name\":\"Падзаросалата\",\"quantity\":\"2\",\"price\":\"410\",\"amount\":\"820\"}]}";

        OrderList orderList = new JsonUtils<OrderList>().readValue(json, OrderList.class);
        Assert.assertEquals(1790, orderList.getTotalPrice());
    }
}
