package com.example.tests;

import io.restassured.RestAssured;
import com.example.model.UserPayload;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.services.UserApiService;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyString;

public class UserApiTests {

    private final UserApiService userApiService = new UserApiService();

    @BeforeAll

    static void setUp() {
        RestAssured.baseURI = "http://159.65.243.118/";
    }

    @Test
    void testCanRegisterUser() {

        UserPayload userPayload = new UserPayload()
                .setUsername(RandomStringUtils.randomAlphanumeric(7))
                .setPassword("123456")
                .setEmail("test@gmail.com");


//        RestAssured
//                .assertThat()
//                .statusCode(200)
//                .body("id", not(isEmptyString()));
        userApiService.registerUser(userPayload)
                .assertThat()
                .statusCode(200)
                .body("id", not(isEmptyString()));
    }

    @Test
    void testCannotRegisterUserWithoutUsername() {
        UserPayload userPayload = new UserPayload()
                .setPassword("")
                .setEmail("");

        userApiService.registerUser(userPayload)
                .assertThat()
                .statusCode(400);
    }

//    @Test
//    void testCanGetCustomersList(){
//        RestAssured
//                .given().contentType(ContentType.JSON).log().all()
//                .when()
//                .get("customers")
//                .then().log().all()
//                .assertThat()
//                .statusCode(200)
//                //.and(ContentType.TEXT)
//                .body("firstName", is("Eve"))
//                .body("lastName", is("Berger"))
//                .body("userName", is("Eve_Berger"))
//
//        ;
//    }

}
