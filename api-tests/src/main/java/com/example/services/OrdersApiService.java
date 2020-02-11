package com.example.services;

import com.example.assertions.AssertableResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrdersApiService extends ApiService {

    public AssertableResponse getOrders() {
        return new AssertableResponse(setUp()
                .when()
                .get("/orders"));
    }

    public AssertableResponse createOrder(){
        return new AssertableResponse(setUp()
        .when()
        .post("/orders"));
    }
}
