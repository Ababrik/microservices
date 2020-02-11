package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.responses.GetOrdersResponse;
import com.example.services.OrdersApiService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static com.example.conditions.Conditions.*;
import static com.example.conditions.Conditions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrderApiTest extends BaseTest {

    private  final OrdersApiService ordersApiService = new OrdersApiService();

    @Test
    void canGetOrders(){
        AssertableResponse orders = ordersApiService.getOrders()
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON));
        assertThat(orders.asPojo(GetOrdersResponse.class).getEmbedded().getCustomerOrders().size(), is(greaterThanOrEqualTo(1)));


    }
}
