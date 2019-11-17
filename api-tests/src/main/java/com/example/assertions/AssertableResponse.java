package com.example.assertions;

import com.example.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class AssertableResponse {
    private final ValidatableResponse response;

    @Step("api response should have {condition}")
    public AssertableResponse shouldHave(Condition condition) {
        log.info("\nAbout to check condition " + condition);
        condition.check(response);
        return this;
    }

}
