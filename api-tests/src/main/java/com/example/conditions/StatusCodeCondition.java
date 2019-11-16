package com.example.conditions;


import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {

    private final Integer expectedStatusCode;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().statusCode(expectedStatusCode);
    }
}
