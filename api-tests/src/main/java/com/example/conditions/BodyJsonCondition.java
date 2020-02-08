package com.example.conditions;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BodyJsonCondition implements Condition{

    private final String json;

    public BodyJsonCondition(String json){
        this.json=json;
    }

    @Override
    public void check(Response response) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(json));
    }
}
