package com.example.conditions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
public class ContentTypeCondition implements Condition {

    private final ContentType expectedContentType;

    @Override
    public void check(Response response) {
        response.then().contentType(expectedContentType);
    }
}
