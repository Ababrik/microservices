package com.example.tests;

import com.example.ProjectConfig;
import com.example.assertions.AssertableResponse;
import com.example.responses.UsersListResponse;
import io.restassured.RestAssured;
import com.example.model.UserPayload;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.services.UserApiService;

import static com.example.conditions.Conditions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;


public class UserApiTests extends BaseTest {

    private final UserApiService userApiService = new UserApiService();

    @BeforeAll

    static void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }

    @Test
    void testCanRegisterUser() {
        String userName = (faker.name().username());
        UserPayload userPayload = new UserPayload()
                .setUsername(userName)
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(userName + "@example.com");

        UsersListResponse u = new UsersListResponse();
        String createdUserId = userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyString())))
                .getValue("id", String.class);
        System.out.println("created UserId: " + createdUserId);
        String returnedUserId = userApiService.getUserById(createdUserId).getValue("id");
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
    void testCanDeleteCustomer() {
        System.out.println(UsersListResponse.class);
        //UsersListResponse.class.
//        userApiService.deletedUser("57a98d98e4b00679b4a830b5")
//         .shouldHave(statusCode(200));
    }

    @Test
    void testCannotDeleteNonexistentCustomer() {
        userApiService.deletedUser("xxx")
                .shouldHave(statusCode(200));
    }

    @Test
    void testCanGetCustomersList() {
        userApiService.getAllUsers()
                .shouldHave(statusCode(200));


        UsersListResponse users = userApiService.getAllUsers().asPojo(UsersListResponse.class);
        assertThat(users.getEmbedded().getCustomer()).hasSizeGreaterThanOrEqualTo(1);


    }

}
