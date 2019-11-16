package com.example.services;

import com.example.model.UserPayload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class UserApiService extends ApiService {
    public ValidatableResponse registerUser(UserPayload userPayload) {
        return setUp()
                .body(userPayload)
                .when()
                .post("register")
                .then();
    }
}