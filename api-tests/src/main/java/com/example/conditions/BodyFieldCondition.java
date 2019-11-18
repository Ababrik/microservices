package com.example.conditions;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matcher;


@RequiredArgsConstructor
@Slf4j
public class BodyFieldCondition implements Condition {

    private final String path;
    private final Matcher matcher;

    public BodyFieldCondition(Matcher matcher) {
        this(null, matcher);
    }

    @Override
    public void check(Response response) {
        if(StringUtils.isBlank(path)){
            response.then().assertThat().body(matcher);
        }else {
            response.then().assertThat().body(path, matcher);
        }
    }

    @Override
    public String toString(){
        if (StringUtils.isBlank(path)){
            return "Body field " + matcher ;
        } else {
            return "Body field " + path + " " + matcher;
        }
    }
}
