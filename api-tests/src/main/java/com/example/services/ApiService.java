package com.example.services;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

public abstract class ApiService {

    public RequestSpecification setUp() {
        return RestAssured.given()
                .accept(ContentType.JSON).log().all()
                .contentType(ContentType.JSON).log().all()
                .filters(new AllureRestAssured(),
                        new RequestLoggingFilter(),
                        new ResponseLoggingFilter());
    }
}
