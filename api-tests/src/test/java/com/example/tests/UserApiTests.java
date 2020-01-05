package com.example.tests;

import com.example.ProjectConfig;
import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.responses.*;
import io.restassured.RestAssured;
import com.example.model.UserPayload;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.example.services.UserApiService;

import java.util.List;

import static com.example.conditions.Conditions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserApiTests extends BaseTest {

    private final UserApiService userApiService = new UserApiService();

    @BeforeAll

    static void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
    }

    @Test
    void testCannotDeleteNonexistentCustomer() {
        userApiService.deletedUser("xxx")
                .shouldHave(statusCode(200));
    }

    @Test
    void testCanRegisterUser() {
        String userName = (faker.name().username());
        UserPayload userPayload = new UserPayload()
                .setUsername(userName)
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(userName + "@example.com");

        String createdUserId = userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyString())))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .getValue("id", String.class);
        userApiService.getUserById(createdUserId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(bodyField("status", is(true)));
    }

    @Test
    void testCannotRegisterUserWithoutUsername() {
        UserPayload userPayload = new UserPayload()
                .setPassword("")
                .setEmail("");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(bodyField(isEmptyOrNullString()));
    }

    @Test
    void testCanDeleteCustomer() {
        String createdUserId = createNewUser().getValue("id");
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json;charset=UTF-8"))
                .shouldHave(bodyField("status", is(true)));
    }


    @Test
    void testCanGetCustomersList() {
        userApiService.getAllUsers()
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json;charset=UTF-8"));

        UsersListResponse users = userApiService.getAllUsers().asPojo(UsersListResponse.class);
        assertThat(users.getEmbedded().getCustomer()).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void testRegisteredUserCanLogin() {
        createNewUser();
        userApiService.login()
                .shouldHave(statusCode(200));
    }

    @Test
    void testCanGetCustomerCard() {
        AssertableResponse cards = userApiService.getCustomerCard("57a98d98e4b00679b4a830af")
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json;charset=UTF-8"));
        Cards card = cards.asPojo(Cards.class);
        assertThat(!card.getHref().isEmpty());
    }

    @Test
    void canAddNewAddress() {
        String createdUserId = createNewUser().getValue("id", String.class);
        AddressPayload addressPayload = new AddressPayload()
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(createdUserId);

        userApiService.createCustomerAddress(addressPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json;charset=UTF-8"))
                .shouldHave(bodyField("href", not(isEmptyString())));
    }

    @Test
    void canGetCustomerAddress() {
        String userId = createNewUser().getValue("id");
        AssertableResponse assertableResponse = addCustomerAddress(userId);

        UserAddressesResponse userAddressesResponse = userApiService.getAllAddress().asPojo(UserAddressesResponse.class);
        List<AddressItem> addressItems = userAddressesResponse.getEmbedded().getAddress();
        assertEquals(7, addressItems.size());

}


}
