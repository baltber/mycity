package ru.mycity.core.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonUtils<T> {


    public JsonNode convertToJson(T t){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.convertValue(t, JsonNode.class);
    }

    public T readValue(String t, Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.readValue(t, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> Map<String, T> readMap(String json, Class<T> tClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, String.class, tClass);
        Map<String, T> ts = mapper.readValue(json, mapType);
        return ts;
    }
}
