package com.example.assertions;

import com.example.conditions.Condition;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssertableResponse {
    private final ValidatableResponse response;

    public AssertableResponse shouldHave(Condition condition) {
        condition.check(response);
        return this;
    }

}
