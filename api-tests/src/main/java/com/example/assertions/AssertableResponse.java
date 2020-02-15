package com.example.assertions;

import com.example.conditions.Condition;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    private final Response response;


    public AssertableResponse shouldHave(Condition condition) {
        condition.check(response);
        return this;
    }

    public <T> T getValue(String jsonPath, Class<T> tClass) {
        return response.jsonPath().getObject(jsonPath, tClass);
    }

    public <T> List<T> getValues(String jsonPath, Class<T> tClass) {
        return (List<T>) response.jsonPath().getObject(jsonPath, tClass);
    }

    public String getValue(String jsonPath) {
        return response.jsonPath().getObject(jsonPath, String.class);
    }

    public <T> T asPojo(Class<T> tClass) {
        String responseBody = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(responseBody, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getCookies(){
        return response.getCookies();
    }

}
