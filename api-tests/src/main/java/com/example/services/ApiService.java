package com.example.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public abstract class ApiService {

    public RequestSpecification setUp() {
        return RestAssured.given().
                contentType(ContentType.JSON).log().all()
                .filters(new AllureRestAssured(),
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }
}
