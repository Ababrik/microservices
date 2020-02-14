package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.model.UserPayload;
import com.example.responses.GetOrdersResponse;
import com.example.services.OrdersApiService;
import com.example.services.UserApiService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static com.example.conditions.Conditions.*;
import static com.example.conditions.Conditions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrderApiTest extends BaseTest {

    private final UserApiService userApiService = new UserApiService();
    private  final OrdersApiService ordersApiService = new OrdersApiService();

    @Test
    void canGetOrders(){
//        UserPayload userDetails = generateUserDetails();
//        AssertableResponse user = userApiService.registerUser(userDetails);
//        userApiService.login(userDetails.getUsername(),userDetails.getPassword());
        userApiService.login("user", "password");
        AssertableResponse orders = ordersApiService.getOrders()
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));
        assertThat(orders.asPojo(GetOrdersResponse.class).getEmbedded().getCustomerOrders().size(), is(greaterThanOrEqualTo(1)));
    }

    @Test
    void canPostOrder(){
        userApiService.login("user", "password");
        ordersApiService.createOrder()
//        .shouldHave(statusCode(200))
        .shouldHave(contentType(ContentType.JSON))
        .shouldHave(bodyJson("jsonSchemas/postOrder_Schema.json"));
    }

}
