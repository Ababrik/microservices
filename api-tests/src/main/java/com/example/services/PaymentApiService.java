package com.example.services;

import com.example.assertions.AssertableResponse;

import java.util.Map;

public class PaymentApiService extends ApiService {

    public AssertableResponse checkHealth() {
        return new AssertableResponse(setUp()
                .when()
                .get("/health"));
    }

    public AssertableResponse doPaymentAuthorisation(Map<String, String> cookies) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .body("")
                .when()
                .post(" /paymentAuth"));
    }
}
