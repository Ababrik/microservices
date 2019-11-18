package com.example.assertions;

import com.example.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {

    private final Response response;

    @Step("api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition) {
        log.info("\nAbout to check condition " + condition);
        condition.check(response);
        return this;
    }

    public <T> T getValue(String jsonPath, Class<T> tClass) {
        return response.jsonPath().getObject(jsonPath, tClass);
    }

    public String getValue(String jsonPath) {
        return response.jsonPath().getObject(jsonPath, String.class);
    }

}
