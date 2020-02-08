package com.example.assertions;

import com.example.conditions.Condition;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    private final Response response;

    // @Step("api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition) {
        //log.info("\nAbout to check condition " + condition);
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



}
