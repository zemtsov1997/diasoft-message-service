package ru.diasoft.message.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class JsonParserTest {

    private final String response1 = "{" +
            "\"id\": 12," +
            "\"cnt\": 2" +
            "}";

    @Test
    void test1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(response1, Map.class);
        assertFalse(map.isEmpty());
    }

}
