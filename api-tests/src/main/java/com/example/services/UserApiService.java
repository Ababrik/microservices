package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.CardPayload;
import com.example.model.UserPayload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiService extends ApiService {

    public AssertableResponse login() {
        return new AssertableResponse(setUp()
                .when()
                .get(" /login"));
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


    public AssertableResponse getUserById(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .get("customers/" + customerId));
    }
    public AssertableResponse getCustomerCardByCustomerId(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .get("/customers/" + customerId + "/cards"));
    }
    public AssertableResponse getAddressesByCutomerId(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .get(" /customers/" + customerId + "/addresses"));
    }

    public AssertableResponse getAllCards() {
        return new AssertableResponse(setUp()
                .when()
                .get("/cards"));
    }

    public AssertableResponse createNewCard(CardPayload cardPayload) {
        return new AssertableResponse(setUp()
                .body(cardPayload)
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


    public AssertableResponse createAddress(AddressPayload addressPayload) {
        return new AssertableResponse((setUp()
                .body(addressPayload)
                .when()
                .post("/addresses")));
    }
    public AssertableResponse deleteAddress(String addressId) {
        return new AssertableResponse(setUp()
                .when()
                .delete("/addresses/" + addressId));
    }

    public AssertableResponse getAddressByAddressId(String addressId) {
        return new AssertableResponse(setUp()
                .when()
                .get(" /addresses/" + addressId));
    }










}