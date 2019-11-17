package com.example.conditions;


import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class StatusCodeCondition implements Condition {

    private final Integer expectedStatusCode;

    @Override
    public void check(ValidatableResponse response) {
        response.assertThat().statusCode(expectedStatusCode);
    }

    @Override
    public String toString(){
        return "Status code is " + expectedStatusCode;
    }
}
