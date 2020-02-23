package com.example.services;

import com.example.assertions.AssertableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OrdersApiService extends ApiService {

    public AssertableResponse getOrders(Map<String, String> cookies) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .get("/orders"));
    }

    public AssertableResponse createOrder(Map<String, String> cookies) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .body("")
                .when()
                .post("/orders"));
    }

    public AssertableResponse deleteOrders(Map<String, String> cookies, String orderId) {
        return new AssertableResponse(setUp()
                .cookies(cookies)
                .when()
                .delete("/orders/" + orderId));
    }
}
