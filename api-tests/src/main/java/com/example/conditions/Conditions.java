package com.example.conditions;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {

    public static StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }


    public static BodyFieldCondition bodyField(String jsonPath, Matcher matcher) {
        return new BodyFieldCondition(jsonPath, matcher);
    }


    public static BodyFieldCondition bodyField(Matcher matcher) {
        return new BodyFieldCondition(matcher);
    }

    public static ContentTypeCondition contentType(ContentType contentType) {
        return new ContentTypeCondition(contentType);
    }

    public static BodyJsonCondition bodyJson(String json) {
        return new BodyJsonCondition(json);
    }
}

