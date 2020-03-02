package com.socks.ui.Page.utils;

import com.example.model.UserPayload;
import com.example.services.UserApiService;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserDetails {
    private final Faker faker = new Faker();


    public Map<String, String> generateUserCredentials() {
        Map<String, String> userCreds = new HashMap<>();
        String userName = (faker.name().username());
        userCreds.put("username", faker.name().username());
        userCreds.put("firstname", faker.name().firstName());
        userCreds.put("lastname", faker.name().lastName());
        userCreds.put("email", userName + "@example.com");
        userCreds.put("password", faker.numerify("a#b##b#a"));
        return userCreds;
    }
}
