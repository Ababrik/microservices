package com.example.services;

import com.example.assertions.AssertableResponse;

public class PaymentApiService extends ApiService {

   public AssertableResponse checkHealth(){
        return new AssertableResponse(setUp()
        .when()
        .get("/health"));
    }

    public AssertableResponse doPaymentAuthorisation(){
       return new AssertableResponse(setUp()
       .when()
       .post(" /paymentAuth"));
    }
}
