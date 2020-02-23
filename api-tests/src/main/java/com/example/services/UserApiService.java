package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.CardPayload;
import com.example.model.UserPayload;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class UserApiService extends ApiService {

    public AssertableResponse login(String username, String password) {
        return new AssertableResponse(setUp()
                .auth().preemptive().basic(username, password)
                .when()
                .get(" login"));
    }

    public AssertableResponse registerUser(UserPayload userPayload) {
        return new AssertableResponse(setUp()
                .body(userPayload)
                .when()
                .post("register"));
    }


    public AssertableResponse getAllUsers() {
        return new AssertableResponse(setUp()
                .when()
                .get("customers/"));
    }


    public AssertableResponse deletedUser(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .delete("customers/" + customerId));
    }


    public AssertableResponse getUserById(Map<String, String> cookies, String customerId) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .get("customers/" + customerId));
    }

    public AssertableResponse getCardByCustomerId(Map<String, String> cookies, String customerId) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .get("/customers/" + customerId + "/cards"));
    }

    public AssertableResponse getAddressesByCutomerId(Map<String, String> cookies, String customerId) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .get(" /customers/" + customerId + "/addresses"));
    }

    public AssertableResponse getAllCards() {
        return new AssertableResponse(setUp()
                .when()
                .get("/cards"));
    }

    public AssertableResponse createNewCard(Map<String, String> cookies, CardPayload cardPayload) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .body(cardPayload)
                .when()
                .post("/cards"));
    }

    public AssertableResponse createNewCardWithoutCardDetails(Map<String, String> cookies, CardPayload emptyCardPayload) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .body(emptyCardPayload)
                .when()
                .post("/cards"));
    }

    public AssertableResponse deleteCardByCardId(String cardId) {
        return new AssertableResponse(setUp()
                .when()
                .delete("/cards/" + cardId));
    }


    public AssertableResponse getCardByCardId(String cardId) {
        return new AssertableResponse(setUp()
                .when()
                .get("/cards/" + cardId));
    }

    public AssertableResponse getAllAddress() {
        return new AssertableResponse((setUp()
                .when()
                .get("/addresses")));
    }


    public AssertableResponse createAddress(Map<String, String> cookies, AddressPayload addressPayload) {
        return new AssertableResponse((setUp()
                .cookies(cookies)
                .body(addressPayload)
                .when()
                .post("/addresses")));
    }

    public AssertableResponse createAddressWithoutAddressDetails(Map<String, String> cookies, AddressPayload emptyAddressPayload) {
        return new AssertableResponse((setUp()
                .cookies(cookies)
                .body(emptyAddressPayload)
                .when()
                .post("/addresses")));
    }

    public AssertableResponse deleteAddress(Map<String, String> cookies, String addressId) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .delete("/addresses/" + addressId));
    }

    public AssertableResponse getAddressByAddressId(Map<String, String> cookies, String addressId) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .get(" /addresses/" + addressId));
    }


}