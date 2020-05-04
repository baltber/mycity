package ru.mycity.core;

import org.junit.Test;
import ru.mycity.core.service.dao.model.ConfigFilter;
import ru.mycity.core.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class OrderServiceTest {

    @Test
    public void test() throws IOException {
        String json = "{\"new\": {\"name\": \"new\",\"filter_id\": 10066,\"container_id\": \"rec172290814\"},\"process\": {\"name\": \"process\",\"filter_id\": 10063,\"container_id\": \"rec172269115\"},\"delivery\": {\"name\": \"delivery\",\"filter_id\": 10064,\"container_id\": \"rec172573790\"}}";

        Map<String, ConfigFilter> filter = new JsonUtils<ConfigFilter>().readMap(json, ConfigFilter.class);
        ConfigFilter configFilter = filter.get("new");
        System.out.println(configFilter);
    }
}
