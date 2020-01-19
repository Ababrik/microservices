package com.example.tests;


import com.example.ProjectConfig;
import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.CardPayload;
import com.example.model.UserPayload;
import com.example.services.UserApiService;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.TimeUnit;


public class BaseTest {

    protected final Faker faker = new Faker();
    private UserApiService userApiService = new UserApiService();

    private UserPayload userPayload = new UserPayload();
    protected AddressPayload addressPayload = new AddressPayload();
    protected CardPayload cardPayload = new CardPayload();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }


    UserPayload generateUserDetails() {
        String userName = faker.name().username();
        return userPayload
                .setUsername(userName)
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(userName + "@example.com");
    }

    CardPayload generateCardDetails(String userId) {
        return cardPayload
                .setLongNum(faker.numerify("##x##y##z###y###z##x####"))
                .setExpires(faker.date().future(1000, TimeUnit.DAYS).toString())
                .setCcv(faker.numerify("###"))
                .setUserID(userId);
    }

    AddressPayload generateAddressDetails(String userId) {
        return addressPayload
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(userId);
    }



}
