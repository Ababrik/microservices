package com.example.tests;


import com.example.ProjectConfig;
import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.UserPayload;
import com.example.services.UserApiService;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;


public class BaseTest {

    protected final Faker faker = new Faker();
    protected UserPayload userPayload = new UserPayload();
    protected UserApiService userApiService = new UserApiService();


    AssertableResponse createNewUser() {
        String userName = (faker.name().username());
        String passw = (faker.numerify("a#b##b#a"));
        userPayload
                .setUsername(userName)
                .setPassword(passw)
                .setEmail(userName + "@example.com");
        return userApiService.registerUser(userPayload);
    }

    AssertableResponse addCustomerAddress(){
        String createdUserId = createNewUser().getValue("id");
        AddressPayload addressPayload = new AddressPayload()
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(createdUserId);
                return  userApiService.createCustomerAddress(addressPayload);
    }

}
