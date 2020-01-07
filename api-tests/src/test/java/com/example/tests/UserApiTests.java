package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.responses.*;
import com.example.model.UserPayload;
import org.junit.jupiter.api.Test;
import com.example.services.UserApiService;


import static com.example.conditions.Conditions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.isEmptyString;


public class UserApiTests extends BaseTest {

    private final UserApiService userApiService = new UserApiService();

    @Test
    void testCanRegisterUser() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType("application/json; charset=utf-8"))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void testCannotRegisterUserWithoutUsername() {
        UserPayload userPayload = new UserPayload()
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400));
//                .shouldHave(contentType("application/json; charset=utf-8"));
    }

    @Test
    void testCannotRegisterUserWithoutPassword() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400));
//                .shouldHave(contentType("application/json; charset=utf-8"));
    }

    @Test
    void testCannotRegisterUserWithoutEmail() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setPassword(faker.numerify("a#b##b#a"));
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400));
//                .shouldHave(contentType("application/json; charset=utf-8"));
    }

    @Test
    void testCanDeleteCustomer() {
        String createdUserId = createNewUser().getValue("id");
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType("application/json;charset=UTF-8"))
                .shouldHave(bodyField("status", is(true)));
    }

    @Test
    void testCannotDeleteNonexistentCustomer() {
        String createdUserId = createNewUser().getValue("id");
        userApiService.deletedUser(createdUserId);
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(400));
//                .shouldHave(contentType("application/json; charset=utf-8"))
//                .shouldHave(bodyField("status", is(false)));
    }

    @Test
    void testCanGetCustomerById() {

        AssertableResponse userAssertableResponse = createNewUser();
        AssertableResponse assertableResponse = userApiService.getUserById(userAssertableResponse.getValue("id"))
                .shouldHave(statusCode(200));
//                .shouldHave(contentType("application/json; charset=utf-8"))
        UserDetailsResponse userDetailsResponse = assertableResponse.asPojo(UserDetailsResponse.class);
        assertThat("firstname",userDetailsResponse.getFirstName(), not(isEmptyOrNullString()));
        assertThat("lastname",userDetailsResponse.getLastName(), not(isEmptyOrNullString()));
        assertThat("username",userDetailsResponse.getUsername().equalsIgnoreCase(userAssertableResponse.getValue("username")));
        assertThat("addresses", userDetailsResponse.getLinks().getAddresses().getHref(), not(nullValue()));
        assertThat("cards", userDetailsResponse.getLinks().getCards().getHref(), not(nullValue()));
        assertThat("self", userDetailsResponse.getLinks().getSelf().getHref(), not(nullValue()));
        assertThat("customer", userDetailsResponse.getLinks().getCustomer().getHref(), not(nullValue()));

    }

    @Test
    void testCanGetCustomersList() {
        userApiService.getAllUsers()
                .shouldHave(statusCode(200));
//                .shouldHave(contentType("application/json;charset=UTF-8"));
        UsersListResponse users = userApiService.getAllUsers().asPojo(UsersListResponse.class);
        assertThat(users.getEmbedded().getCustomer()).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    void testCanGetCustomerCard() {
        AssertableResponse customerCards = userApiService.getCustomerCard(createNewUser().getValue("id"))
                .shouldHave(statusCode(200));
//                .shouldHave(contentType("application/json;charset=UTF-8"));
        Cards card = customerCards.asPojo(Cards.class);
        assertThat(!card.getHref().isEmpty());
    }

    @Test
    void testCanAddNewAddress() {
        AddressPayload addressPayload = new AddressPayload()
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(createNewUser().getValue("id"));

        AssertableResponse assertableResponse =userApiService.createCustomerAddress(addressPayload)
                .shouldHave(statusCode(200));
//                .shouldHave(contentType("application/json;charset=UTF-8"))
//                .shouldHave(bodyField("id", not(isEmptyString())));
        UserAddedAddressResponse userAddedAddressResponse = assertableResponse.asPojo(UserAddedAddressResponse.class);
        assertThat(!userAddedAddressResponse.getId().isEmpty());
    }

    @Test
    void testCanGetAllAddresses() {
        addCustomerAddress(createNewUser().getValue("id"));
        UserAddressesResponse userAddressesResponse = userApiService.getAllAddress().asPojo(UserAddressesResponse.class);
        assertThat(userAddressesResponse.getEmbedded().getAddress().size() >= 1);
    }

    @Test
    void testCanDeleteAddress() {
        String addressId = addCustomerAddress(createNewUser().getValue("id")).getValue("id");
        AssertableResponse assertableResponse = userApiService.deleteAddress("123")
                .shouldHave(statusCode(200));
//                .shouldHave(contentType("application/json;charset=UTF-8"))
//                .shouldHave(bodyField("status", is(true)));
        UserDeletedAddressResponse userDeletedAddressResponse = assertableResponse.asPojo(UserDeletedAddressResponse.class);
                assertThat(userDeletedAddressResponse.isStatus());
    }
    @Test
    void testCanGetAddressById() {
        String addressId = addCustomerAddress(createNewUser().getValue("id")).getValue("id");
        userApiService.getAddressById(addressId)
                .shouldHave(statusCode(200));
//                .shouldHave(contentType("application/json;charset=UTF-8"));
    }


}
