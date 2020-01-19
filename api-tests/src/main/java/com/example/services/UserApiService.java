package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
import com.example.model.CardPayload;
import com.example.model.UserPayload;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiService extends ApiService {
    //@Step
    public AssertableResponse registerUser(UserPayload userPayload) {
        //log.info("\nAbout to create new user " + userPayload);
        return new AssertableResponse(setUp()
                .body(userPayload)
                .when()
                .post("register"));
    }

    //@Step
    public AssertableResponse deletedUser(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .delete("customers/" + customerId));
    }

    //@Step
    public AssertableResponse getUserById(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .get("customers/" + customerId));
    }

    // @Step
    public AssertableResponse getAllUsers() {
        return new AssertableResponse(setUp()
                .when()
                .get("customers/"));
    }

    public AssertableResponse login() {
        return new AssertableResponse(setUp()
                .when()
                .get(" /login"));
    }



    public AssertableResponse createAddress(AddressPayload addressPayload) {
        return new AssertableResponse((setUp()
                .body(addressPayload)
                .when()
                .post("/addresses")));
    }

    public AssertableResponse getAllAddress() {
        return new AssertableResponse((setUp()
                .when()
                .get("/addresses")));
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

    public AssertableResponse getAddressesByCutomerId(String customerId){
        return new AssertableResponse(setUp()
        .when()
        .get(" /customers/" + customerId + "/addresses"));
    }

    public AssertableResponse getCustomerCardByCustomerId(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .get("/customers/" + customerId + "/cards"));
    }

    public AssertableResponse createNewCard(CardPayload cardPayload) {
        return new AssertableResponse(setUp()
                .body(cardPayload)
                .when()
                .post("/cards"));
    }

    public AssertableResponse deleteCardByCardId(String cardId){
        return new AssertableResponse(setUp()
        .when()
        .delete("/cards/" + cardId));
    }

    public AssertableResponse getAllCards(){
        return new AssertableResponse(setUp()
        .when()
        .get("/cards"));
    }

    public AssertableResponse getCardByCardId(String cardId){
        return new AssertableResponse(setUp()
        .when()
        .get("/cards/" + cardId));
    }

}