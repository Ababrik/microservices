package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.CardPayload;
import com.example.responses.*;
import com.example.model.UserPayload;
import com.example.services.UserApiService;

import com.example.utils.UserApiServiceUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static com.example.conditions.Conditions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


public class UserApiTests extends BaseTest {
private  UserApiServiceUtils userApiServiceUtils = new UserApiServiceUtils();
    private final UserApiService userApiService = new UserApiService();


    @Test
    void canLoginAsDefaultUser() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload);
        userApiService.login(userPayload.getUsername(), userPayload.getPassword())
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getLoggedInCustomerSchema.json"));
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
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));
    }

    @Test
    void testCannotDeleteNonexistentCustomer() {
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        userApiService.deletedUser(createdUserId);
        userApiService.deletedUser(createdUserId)
                .shouldHave(statusCode(400))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("customer is not found")));
    }

    @Test
    void testCanGetCustomerById() {
        UserPayload generatedUserDetails = userApiServiceUtils.generateUserDetails();
        String createdUserId = userApiService.registerUser(generatedUserDetails).getValue("id");
        AssertableResponse userAssertableResponse = userApiService.getUserById(createdUserId)
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCustomerByCustomerIdSchema.json"));
        GetCustomerByCustomerIdResponse userDetailsResponse = userAssertableResponse.asPojo(GetCustomerByCustomerIdResponse.class);
        assertThat("username", userDetailsResponse.getUsername().equalsIgnoreCase(generatedUserDetails.getUsername()));


    }

    @Test
    void testCanGetCustomersList() {
        AssertableResponse usersResponse = userApiService.getAllUsers()
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAllCustomersSchema.json"));
        GetAllCustomersResponse users = usersResponse.asPojo(GetAllCustomersResponse.class);
        assertThat(users.getEmbedded().getCustomer(), hasSize(greaterThanOrEqualTo(2)));
    }


    @Test
    void canCreateCard() {
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        CardPayload cardPayload=new CardPayload()
                .setLongNum(faker.numerify("##x##y##z###y###z##x####"))
                .setExpires(faker.date().future(1000, TimeUnit.DAYS).toString())
                .setCcv(faker.numerify("###"))
                .setUserID(createdUserId);
        AssertableResponse card = userApiService.createNewCard(cardPayload)
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
//                .shouldHave(bodyField("id", not(isEmptyString())));
        String id = card.asPojo(PostCardResponse.class).getId();
        assertThat(id, not(isEmptyString()));
    }

    @Test
    void canDeleteCard() {
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        String createdCardId = userApiService.createNewCard(userApiServiceUtils.generateCardDetails(createdUserId)).getValue("id");
        AssertableResponse id = userApiService.deleteCardByCardId(createdCardId)
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
//                .shouldHave(bodyField("status", is(true)));
        boolean status = id.asPojo(DeleteCardResponse.class).isStatus();
        assertThat(status, is(true));
    }

    @Test
    void canGetCardsByCustomerId() {
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        userApiService.createNewCard(userApiServiceUtils.generateCardDetails(createdUserId));
        AssertableResponse customerCards = userApiService.getCustomerCardByCustomerId(createdUserId)
                .shouldHave(statusCode(200))
                .shouldHave(bodyJson("jsonSchemas/getCardsByCustomerIdSchema.json"))
                .shouldHave(contentType(ContentType.JSON));
        assertThat("cards quantity", customerCards.asPojo(GetCustomerCardsByCustomerIdResponse.class).getEmbedded().getCard(), hasSize(greaterThanOrEqualTo(2)));
    }

    @Test
    void canGetCardByCardId() {
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        String createdCardId = userApiService.createNewCard(userApiServiceUtils.generateCardDetails(createdUserId)).getValue("id");

        userApiService.getCardByCardId(createdCardId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCardByCardIdSchema.json"));
    }

    @Test
    void canGetAllCards() {
        String createdUserId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        userApiService.createNewCard(userApiServiceUtils.generateCardDetails(createdUserId));
        AssertableResponse cardsResponse = userApiService.getAllCards()
                .shouldHave(bodyJson("jsonSchemas/getAllCardsSchema.json"));
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON));
//        GetAllCardsResponse cardsListResponse = cardsResponse.asPojo(GetAllCardsResponse.class);
//        assertThat("cards quantity", cardsListResponse.getEmbedded().getCard(), hasSize(greaterThanOrEqualTo(3)));


    }

    @Test
    void canAddNewAddress() {
        String userId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
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
        String userId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        userApiService.createAddress(userApiServiceUtils.generateAddressDetails(userId));
        AssertableResponse userAddressesResponse = userApiService.getAllAddress()
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAllAddressesSchema.json"));
//        assertThat(userAddressesResponse.asPojo(GetAllAddressesResponse.class).getEmbedded().getAddress().get(0)., is(7) );

    }

    @Test
    void canDeleteAddress() {
        String userId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        String addressId = userApiService.createAddress(userApiServiceUtils.generateAddressDetails(userId)).getValue("id");
        userApiService.deleteAddress(addressId)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));
    }


    @Test
    void canGetAddressByAddressId() {

        String userId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        AddressPayload addressPayload = userApiServiceUtils.generateAddressDetails(userId);
        String addressId = userApiService.createAddress(addressPayload).getValue("id");

        AssertableResponse addressAssertableResponse = userApiService.getAddressByAddressId(addressId)
//                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAddressByAddressIdSchema.json"));
        GetAddressByAddressIdResponse addressByAddressIdResponse = addressAssertableResponse.asPojo(GetAddressByAddressIdResponse.class);
        assertThat("number", addressByAddressIdResponse.getNumber(), is(addressPayload.getNumber()));
    }

    @Test
    void canGetAddressesByCustomerId() {
        String userId = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getValue("id");
        userApiService.createAddress(userApiServiceUtils.generateAddressDetails(userId));
        AssertableResponse addressesAssertableResponse = userApiService.getAddressesByCutomerId(userId)
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAddressByCustomerIdSchema.json"));

        GetCustomerAddressesByCustomerIdResponse addressesByCustomerIdResponse = addressesAssertableResponse.asPojo(GetCustomerAddressesByCustomerIdResponse.class);
        assertThat(addressesByCustomerIdResponse.getEmbedded().getAddress(), hasSize(greaterThanOrEqualTo(1)));
    }


}
