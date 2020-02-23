package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.model.CardPayload;
import com.example.responses.*;
import com.example.model.UserPayload;
import com.example.services.UserApiService;

import com.example.utils.UserApiServiceUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static com.example.conditions.Conditions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


public class UserApiTests extends BaseTest {
    private UserApiServiceUtils userApiServiceUtils = new UserApiServiceUtils();
    private final UserApiService userApiService = new UserApiService();


    @Test
    void canLoginAsUser() {
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
        userApiService.login(userPayload.getUsername(), userPayload.getPassword())
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getLoggedInCustomerSchema.json"));

    }

    @Test
    void testCanRegisterUserWithUsernamePasswordEmail() {
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void testCannotRegisterUserWithoutUsername() {
        UserPayload userPayload = new UserPayload()
                .setPassword(faker.numerify("a#b##b#a"));
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("username is required")));
    }

    @Test
    void testCannotRegisterUserWithoutPassword() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username());
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("password is required")));
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
        AssertableResponse registeredUser = userApiService.registerUser(generatedUserDetails);
        AssertableResponse userAssertableResponse = userApiService.getUserById(registeredUser.getCookies(), registeredUser.getValue("id"))
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCustomerByCustomerIdSchema.json"));
        GetCustomerByCustomerIdResponse userDetailsResponse = userAssertableResponse.asPojo(GetCustomerByCustomerIdResponse.class);
        assertThat("username", userDetailsResponse.getUsername().equalsIgnoreCase(generatedUserDetails.getUsername()));
    }

    @Test
    void cannotGetNonexistentCustomer(){
        UserPayload generatedUserDetails = userApiServiceUtils.generateUserDetails();
        AssertableResponse registeredUser = userApiService.registerUser(generatedUserDetails);
        userApiService.deletedUser(registeredUser.getValue("id"));
        userApiService.getUserById(registeredUser.getCookies(), registeredUser.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("customer is not found")));
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
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        CardPayload cardPayload = userApiServiceUtils.generateCardDetails(createdUser.getValue("id"));
        AssertableResponse card = userApiService.createNewCard(createdUser.getCookies(), cardPayload)
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
//                .shouldHave(bodyField("id", not(isEmptyString())));
        String id = card.asPojo(PostCardResponse.class).getId();
        assertThat(id, not(isEmptyString()));
    }

    @Test
    void canCreateCardWithoutCardItemDetails() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse card = userApiService.createNewCardWithoutCardDetails(createdUser.getCookies(), userApiServiceUtils.setEmptyCardDetails())
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));
        assertThat(card.asPojo(PostCardResponse.class).getId(), not(isEmptyString()));
    }

    @Test
    void canDeleteCard() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdCard = userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        AssertableResponse id = userApiService.deleteCardByCardId(createdCard.getValue("id"))
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
//                .shouldHave(bodyField("status", is(true)));
        assertThat(id.asPojo(DeleteCardResponse.class).isStatus(), is(true));
    }

    @Test
    void cannotDeleteNonexistentCard() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdCard = userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        userApiService.deleteCardByCardId(createdCard.getValue("id"));
        userApiService.deleteCardByCardId(createdCard.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("card is not found")));
    }


    @Test
    void canGetCardsByCustomerId() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        AssertableResponse customerCards = userApiService.getCardByCustomerId(createdUser.getCookies(), createdUser.getValue("id"))
                .shouldHave(statusCode(200))
                .shouldHave(bodyJson("jsonSchemas/getCardsByCustomerIdSchema.json"))
                .shouldHave(contentType(ContentType.JSON));
        assertThat("cards quantity", customerCards.asPojo(GetCustomerCardsByCustomerIdResponse.class).getEmbedded().getCard(), hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    void cannotGetCardByNonexistentCustomerId(){
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        userApiService.deletedUser(createdUser.getValue("id"));
        userApiService.getCardByCustomerId(createdUser.getCookies(), createdUser.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error",is("customer is not found")));
    }


    @Test
    void canGetCardByCardId() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdCard = userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        userApiService.getCardByCardId(createdCard.getValue("id"))
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCardByCardIdSchema.json"));
    }

    @Test
    void cannotGetNonexistentCard(){
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdCard = userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        userApiService.deleteCardByCardId(createdCard.getValue("id"));
        userApiService.getCardByCardId(createdCard.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("card is not found")));
    }

    @Test
    void canGetAllCards() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdCard = userApiService.createNewCard(createdUser.getCookies(), userApiServiceUtils.generateCardDetails(createdUser.getValue("id")));
        AssertableResponse cardsResponse = userApiService.getAllCards()
                .shouldHave(bodyJson("jsonSchemas/getAllCardsSchema.json"));
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON));
//        GetAllCardsResponse cardsListResponse = cardsResponse.asPojo(GetAllCardsResponse.class);
        assertThat("cards quantity", cardsResponse.asPojo(GetAllCardsResponse.class).getEmbedded().getCard(), hasSize(greaterThanOrEqualTo(3)));


    }

    @Test
    void canAddNewAddress() {
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        AssertableResponse createdUser = userApiService.registerUser(userPayload);
        AssertableResponse address = userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")))
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
//                .shouldHave(bodyField("id", not(isEmptyString())));
        assertThat(address.asPojo(PostAddressResponse.class).getId(), not(isEmptyString()));
    }

    @Test
    void canAddAddressWithoutAddressDetails() {
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        AssertableResponse createdUser = userApiService.registerUser(userPayload);
        userApiService.createAddressWithoutAddressDetails(createdUser.getCookies(), userApiServiceUtils.setEmptyAddressPayload())
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void canGetAllAddresses() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        AssertableResponse userAddressesResponse = userApiService.getAllAddress()
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAllAddressesSchema.json"));

    }

    @Test
    void canDeleteAddress() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdAdsress = userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        AssertableResponse deletedAddress = userApiService.deleteAddress(createdUser.getCookies(), createdAdsress.getValue("id"))
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));
//        assertThat(deletedAddress.asPojo(DeleteAddressResponse.class),is(true));
    }

    @Test
    void cannotDeleteNonexistentAddress() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdAdsress = userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        userApiService.deleteAddress(createdUser.getCookies(), createdAdsress.getValue("id"));
        userApiService.deleteAddress(createdUser.getCookies(), createdAdsress.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("Address is not found")));
    }


    @Test
    void canGetAddressByAddressId() {
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdAddress = userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        AssertableResponse addressAssertableResponse = userApiService.getAddressByAddressId(createdUser.getCookies(), createdAddress.getValue("id"))
//                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAddressByAddressIdSchema.json"));
    }

    @Test
    void cannotGetNonexistentAddressByAddressId(){
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        AssertableResponse createdAddress = userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        userApiService.deleteAddress(createdUser.getCookies(), createdAddress.getValue("id"));
        userApiService.getAddressByAddressId(createdUser.getCookies(), createdAddress.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("address is not found")));
    }

    @Test
    void canGetAddressesByCustomerId() {

        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        AssertableResponse addressesAssertableResponse = userApiService.getAddressesByCutomerId(createdUser.getCookies(), createdUser.getValue("id"))
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getAddressByCustomerIdSchema.json"));

        GetCustomerAddressesByCustomerIdResponse addressesByCustomerIdResponse = addressesAssertableResponse.asPojo(GetCustomerAddressesByCustomerIdResponse.class);
        assertThat(addressesByCustomerIdResponse.getEmbedded().getAddress(), hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    void cannotGetAddressByNonexistentCustomerId(){
        AssertableResponse createdUser = userApiService.registerUser(userApiServiceUtils.generateUserDetails());
        userApiService.createAddress(createdUser.getCookies(), userApiServiceUtils.generateAddressDetails(createdUser.getValue("id")));
        userApiService.deletedUser(createdUser.getValue("id"));
        userApiService.getAddressesByCutomerId(createdUser.getCookies(), createdUser.getValue("id"))
                .shouldHave(statusCode(404))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", is("user is not found")));
    }


}
