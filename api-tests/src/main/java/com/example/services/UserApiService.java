package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.UserPayload;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiService extends ApiService {
    @Step
    public AssertableResponse registerUser(UserPayload userPayload) {
        log.info("\nAbout to create new user " + userPayload);
        return new AssertableResponse(setUp()
                .body(userPayload)
                .when()
                .post("register"));
    }
}