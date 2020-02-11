package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.responses.*;
import com.example.model.UserPayload;
import com.example.services.UserApiService;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static com.example.conditions.Conditions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


public class UserApiTests extends BaseTest {

    private final UserApiService userApiService = new UserApiService();


    @Test
    void canLoginAsDefaultUser() {
        userApiService.login()
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getLoggedInCustomer_Schema.json"));
    }

    @Test
    void testCanRegisterUserWithUsernamePasswordEmail() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void testCannotRegisterUserWithoutUsername() {
        UserPayload userPayload = new UserPayload()
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", not(isEmptyString())));
    }

    @Test
    void testCannotRegisterUserWithoutPassword() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", not(isEmptyString())));
    }

    @Test
    void testCanRegisterUserWithoutEmail() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setPassword(faker.numerify("a#b##b#a"));
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void testCanDeleteCustomer() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));
    }

    @Test
    void testCannotDeleteNonexistentCustomer() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.deletedUser(createdUserId);
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(400))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("customer is not found")));
    }

    @Test
    void testCanGetCustomerById() {
        UserPayload generatedUserDetails = generateUserDetails();
        String createdUserId = userApiService.registerUser(generatedUserDetails).getValue("id");
        AssertableResponse userAssertableResponse = userApiService.getUserById(createdUserId)

                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCustomerByCustomerId_Schema.json"));

        Get_CustomerByCustomerId_Response userDetailsResponse = userAssertableResponse.asPojo(Get_CustomerByCustomerId_Response.class);
        assertThat("username", userDetailsResponse.getUsername().equalsIgnoreCase(generatedUserDetails.getUsername()));


    }

    @Test
    void testCanGetCustomersList() {
        AssertableResponse usersResponse = userApiService.getAllUsers()
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON));
//        .shouldHave(bodyJson("jsonSchemas/getAllCustomers_Schema.json"));
        Get_AllCustomers_Response users = usersResponse.asPojo(Get_AllCustomers_Response.class);
        assertThat(users.getEmbedded().getCustomer(), hasSize(greaterThanOrEqualTo(2)));

    }


    @Test
    void canCreateCard() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        cardPayload
                .setLongNum(faker.numerify("##x##y##z###y###z##x####"))
                .setExpires(faker.date().future(1000, TimeUnit.DAYS).toString())
                .setCcv(faker.numerify("###"))
                .setUserID(createdUserId);
        userApiService.createNewCard(cardPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void canDeleteCard() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        String createdCardId = userApiService.createNewCard(generateCardDetails(createdUserId)).getValue("id");
        userApiService.deleteCardByCardId(createdCardId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));
    }

    @Test
    void canGetCardsByCustomerId() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createNewCard(generateCardDetails(createdUserId));
        AssertableResponse customerCards = userApiService.getCustomerCardByCustomerId(createdUserId)
                .shouldHave(statusCode(200))
                .shouldHave(bodyJson("jsonSchemas/getCardsByCustomerId_Schema.json"))
                .shouldHave(contentType(ContentType.JSON));
        assertThat("cards quantity", customerCards.asPojo(Get_CustomerCardsByCustomerId_Response.class).getEmbedded().getCard(), hasSize(greaterThanOrEqualTo(2)));
    }

    @Test
    void canGetCardByCardId() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        String createdCardId = userApiService.createNewCard(generateCardDetails(createdUserId)).getValue("id");

        AssertableResponse cardResponse = userApiService.getCardByCardId(createdCardId)
                .shouldHave(statusCode(200))
                .shouldHave(bodyJson("jsonSchemas/getCardByCardId_Schema.json"))
                .shouldHave(contentType(ContentType.JSON));
    }

    @Test
    void canGetAllCards() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createNewCard(generateCardDetails(createdUserId));
        AssertableResponse cardsResponse = userApiService.getAllCards()
                .shouldHave(bodyJson("jsonSchemas/getAllCards_Schema.json"));
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON));
        Get_AllCards_Response cardsListResponse = cardsResponse.asPojo(Get_AllCards_Response.class);
        assertThat("cards quantity", cardsListResponse.getEmbedded().getCard(), hasSize(greaterThanOrEqualTo(3)));


    }

    @Test
    void canAddNewAddress() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        AddressPayload addressPayload = new AddressPayload()
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(userId);

        userApiService.createAddress(addressPayload)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));

    }

    @Test
    void canGetAllAddresses() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createAddress(generateAddressDetails(userId));
        AssertableResponse userAddressesResponse = userApiService.getAllAddress()
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAllAddresses_Schema.json"));
    }

    @Test
    void canDeleteAddress() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        String addressId = userApiService.createAddress(generateAddressDetails(userId)).getValue("id");
        userApiService.deleteAddress(addressId)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));
    }


    @Test
    void canGetAddressByAddressId() {

        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        AddressPayload addressPayload = generateAddressDetails(userId);
        String addressId = userApiService.createAddress(addressPayload).getValue("id");

        AssertableResponse addressAssertableResponse = userApiService.getAddressByAddressId(addressId)
//                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAddressByAddressId_Schema.json"));
        Get_AddressByAddressId_Response addressByAddressIdResponse = addressAssertableResponse.asPojo(Get_AddressByAddressId_Response.class);
        assertThat("number", addressByAddressIdResponse.getNumber(), is(addressPayload.getNumber()));
    }

    @Test
    void canGetAddressesByCustomerId() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createAddress(generateAddressDetails(userId));
        AssertableResponse addressesAssertableResponse = userApiService.getAddressesByCutomerId(userId)
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAddressByCustomerId_Schema.json"));

        Get_CustomerAddressesByCustomerId_Response addressesByCustomerIdResponse = addressesAssertableResponse.asPojo(Get_CustomerAddressesByCustomerId_Response.class);
        assertThat(addressesByCustomerIdResponse.getEmbedded().getAddress(), hasSize(greaterThanOrEqualTo(1)));
    }


}
