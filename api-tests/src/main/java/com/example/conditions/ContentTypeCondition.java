package com.example.conditions;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;


@RequiredArgsConstructor
@Slf4j
public class ContentTypeCondition implements Condition {

    private final ContentType expectedContentType;

    @Override
    public void check(Response response) {
        response.then().contentType(expectedContentType);
    }
}
