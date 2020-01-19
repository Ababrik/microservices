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
    void testCanRegisterUser() {
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
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("error", not(isEmptyString())));
    }

    @Test
    void testCannotRegisterUserWithoutPassword() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setEmail(faker.name().username() + "@example.com");
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
//                .shouldHave(contentType(ContentType.JSON));
                .shouldHave(bodyField("error", not(isEmptyString())));
    }

    @Test
    void testCannotRegisterUserWithoutEmail() {
        UserPayload userPayload = new UserPayload()
                .setUsername(faker.name().username())
                .setPassword(faker.numerify("a#b##b#a"));
        userApiService.registerUser(userPayload)
                .shouldHave(statusCode(400))
//                .shouldHave(contentType(ContentType.JSON));
                .shouldHave(bodyField("error", not(isEmptyString())));
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
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON))
        UserDetailsResponse userDetailsResponse = userAssertableResponse.asPojo(UserDetailsResponse.class);
        assertThat("firstname", userDetailsResponse.getFirstName(), not(isEmptyOrNullString()));
        assertThat("lastname", userDetailsResponse.getLastName(), not(isEmptyOrNullString()));
        assertThat("username", userDetailsResponse.getLastName(), not(isEmptyOrNullString()));
        assertThat("username", userDetailsResponse.getUsername().equalsIgnoreCase(generatedUserDetails.getUsername()));
        assertThat("self", userDetailsResponse.getLinks().getSelf().getHref(), not(nullValue()));
        assertThat("addresses", userDetailsResponse.getLinks().getAddresses().getHref(), not(nullValue()));
        assertThat("customer", userDetailsResponse.getLinks().getCustomer().getHref(), not(nullValue()));
        assertThat("cards", userDetailsResponse.getLinks().getCards().getHref(), not(nullValue()));

    }

    @Test
    void testCanGetCustomersList() {
        AssertableResponse usersResponse = userApiService.getAllUsers()
                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON));
        UsersListResponse users = usersResponse.asPojo(UsersListResponse.class);
        assertThat(users.getEmbedded().getCustomer(), hasSize(greaterThanOrEqualTo(2)));
    }


    @Test
    void canCreateCard(){
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        cardPayload
                .setLongNum(faker.numerify("##x##y##z###y###z##x####"))
                .setExpires(faker.date().future(1000, TimeUnit.DAYS).toString())
                .setCcv(faker.numerify("###"))
                .setUserID(createdUserId);

        AssertableResponse cardResponse = userApiService.createNewCard(cardPayload)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
    }

    @Test
    void canDeleteCard(){
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        String cardId = userApiService.createNewCard(generateCardDetails(createdUserId)).getValue("id");
        AssertableResponse detedCardRepsponse = userApiService.deleteCardByCardId(cardId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status",is(true) ));
    }

    @Test
    void testCanGetCustomerCardByCustomerId() {
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createNewCard(generateCardDetails(createdUserId));

        AssertableResponse customerCards = userApiService.getCustomerCardByCustomerId(createdUserId);
//                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON));
        UserCardsResponse userCardsResponse = customerCards.asPojo(UserCardsResponse.class);
        assertThat(userCardsResponse.getLongNum(), not(isEmptyString()));
        assertThat(userCardsResponse.getExpires(), not(isEmptyString()));
        assertThat(userCardsResponse.getCcv(), not(isEmptyString()));
        assertThat(userCardsResponse.getLinks(), is(notNullValue()));
    }

    @Test
    void testCanGetCardByCardId(){
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        String createdCardId = userApiService.createNewCard(generateCardDetails(createdUserId)).getValue("id");

        AssertableResponse cardResponse = userApiService.getCardByCardId(createdCardId)
        .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));
        CardByCardIdResponse cardByCardIdResponse = cardResponse.asPojo(CardByCardIdResponse.class);
        assertThat(cardByCardIdResponse.getLinks().getCards().getHref(), not(isEmptyString()));


    }

    @Test
    void testCanGetAllCards(){
        String createdUserId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createNewCard(generateCardDetails(createdUserId));
        AssertableResponse cardsResponse= userApiService.getAllCards()
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));

    }

    @Test
    void testCanAddNewAddress() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        AddressPayload addressPayload = new AddressPayload()
                .setStreet(faker.address().streetName())
                .setNumber(faker.address().buildingNumber())
                .setCountry(faker.address().country())
                .setCity(faker.address().city())
                .setPostcode(faker.address().zipCode())
                .setUserID(userId);

//        AddedAddressResponse userAddedAddressResponse =
        userApiService.createAddress(addressPayload)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("id", not(isEmptyString())));
//                .asPojo(AddedAddressResponse.class);
//
//        assertThat(userAddedAddressResponse.getId().length()>1);
    }

    @Test
    void testCanGetAllAddresses() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        userApiService.createAddress(generateAddressDetails(userId));
        UserAddressesResponse userAddressesResponse = userApiService.getAllAddress().asPojo(UserAddressesResponse.class);
        assertThat(userAddressesResponse.getEmbedded().getAddress(), is(notNullValue()));
        assertThat(userAddressesResponse.getEmbedded().getAddress(), hasItems());
    }

    @Test
    void testCanDeleteAddress() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        String addressId = userApiService.createAddress(generateAddressDetails(userId)).getValue("id");
        userApiService.deleteAddress(addressId)
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("status", is(true)));

    }


    @Test
    void testCanGetAddressByAddressId() {

        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        AddressPayload addressPayload = generateAddressDetails(userId);
        String addressId = userApiService.createAddress(addressPayload).getValue("id");

        AssertableResponse addressAssertableResponse = userApiService.getAddressByAddressId(addressId);
//                .shouldHave(statusCode(200));
//                .shouldHave(contentType(ContentType.JSON));
        AddressByAddressIdResponse addressByAddressIdResponse = addressAssertableResponse.asPojo(AddressByAddressIdResponse.class);
        assertThat("number", addressByAddressIdResponse.getNumber(), is(addressPayload.getNumber()));
    }

    @Test
    void testCanGetAddressesByCustomerId() {
        String userId = userApiService.registerUser(generateUserDetails()).getValue("id");
        AddressPayload addressPayload = generateAddressDetails(userId);
        String addressId = userApiService.createAddress(addressPayload).getValue("id");

        AssertableResponse addressesAssertableRrsponse = userApiService.getAddressesByCutomerId(userId)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));

        AddressesByCustomerIdResponse addressesByCustomerIdResponse = addressesAssertableRrsponse.asPojo(AddressesByCustomerIdResponse.class);
        assertThat(addressesByCustomerIdResponse.getEmbedded().getAddress(), hasSize(greaterThanOrEqualTo(1)));
    }



}
