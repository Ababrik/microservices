package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.responses.GetOrdersResponse;
import com.example.services.OrdersApiService;
import com.example.services.UserApiService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.example.conditions.Conditions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class OrderApiTest extends BaseTest {

    private final UserApiService userApiService = new UserApiService();
    private  final OrdersApiService ordersApiService = new OrdersApiService();

    @Test
    void canGetOrders(){
//        UserPayload userDetails = generateUserDetails();
//        AssertableResponse user = userApiService.registerUser(userDetails);
//        userApiService.login(userDetails.getUsername(),userDetails.getPassword());
        Map<String, String> cookies = userApiService.login("user", "password").getCookies();
        AssertableResponse orders = ordersApiService.getOrders(cookies)
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON));
        .shouldHave(bodyJson("jsonSchemas/getOrdersSchema.json"));
//        assertThat(orders.asPojo(GetOrdersResponse.class).getEmbedded().getCustomerOrders().size(), is(greaterThanOrEqualTo(1)));

    }

    @Test
    void canPostOrder(){
        Map<String, String> cookies = userApiService.login("user", "password").getCookies();
        userApiService.login("user", "password");
        ordersApiService.createOrder(cookies)
//        .shouldHave(statusCode(201))
//        .shouldHave(contentType(ContentType.JSON))
        .shouldHave(bodyJson("jsonSchemas/postOrderSchema.json"));
    }

}
