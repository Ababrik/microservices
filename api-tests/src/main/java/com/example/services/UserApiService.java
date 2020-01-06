package com.example.services;

import com.example.assertions.AssertableResponse;
import com.example.model.AddressPayload;
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

    public AssertableResponse getCustomerCard(String customerId) {
        return new AssertableResponse(setUp()
                .when()
                .get("/customers/" + customerId + "/cards"));
    }

    public AssertableResponse createCustomerAddress(AddressPayload addressPayload) {
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


    public AssertableResponse deleteAddress(String id){
        return new AssertableResponse(setUp()
        .when()
        .delete("/addresses/"+id));
    }

    public AssertableResponse getAddressById(String id){
        return new AssertableResponse(setUp()
        .when()
        .get(" /addresses/"+id));

    }

}