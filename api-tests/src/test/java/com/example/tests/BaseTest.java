package com.example.tests;


import com.example.ProjectConfig;
import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.UserPayload;
import com.example.services.UserApiService;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {

    protected final Faker faker = new Faker();
    protected UserPayload userPayload = new UserPayload();
    protected UserApiService userApiService = new UserApiService();


    @BeforeAll

    static void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }
    
    
    AssertableResponse createNewUser() {
        String userName= faker.name().username();
             userPayload
                .setUsername(userName)
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(userName + "@example.com");
        return userApiService.registerUser(userPayload);
    }

    AssertableResponse addCustomerAddress(String userId){

        AddressPayload addressPayload = new AddressPayload()
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(userId);
                return  userApiService.createCustomerAddress(addressPayload);
    }

}
