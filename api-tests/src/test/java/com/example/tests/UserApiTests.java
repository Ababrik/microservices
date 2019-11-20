package com.example.tests;

import com.example.responses.UsersListResponse;
import io.restassured.RestAssured;
import com.example.model.UserPayload;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.services.UserApiService;

import static com.example.conditions.Conditions.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.isEmptyOrNullString;
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


        String id = userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyString())))
                .shouldHave(bodyField(containsString("id")))
                .getValue("id", String.class);
UsersListResponse users = userApiService.getAllUsers().asPojo(UsersListResponse.class);

    }

    @Test
    void testCannotRegisterUserWithoutUsername() {
        UserPayload userPayload = new UserPayload()
                .setPassword("")
                .setEmail("");

        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
                .shouldHave(bodyField(isEmptyOrNullString()));
    }

    @Test
    void testCanDeleteCustomer(){
        userApiService.deletedUser("57a98d98e4b00679b4a830b5")
         .shouldHave(statusCode(200));
    }

    @Test
    void testCannotDeleteNonexistentCustomer(){
        userApiService.deletedUser("xxx")
                .shouldHave(statusCode(200));
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
