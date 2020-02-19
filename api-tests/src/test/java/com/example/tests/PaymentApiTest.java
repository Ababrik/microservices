package com.example.tests;
import static com.example.conditions.Conditions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import com.example.assertions.AssertableResponse;
import com.example.responses.GetHealthResponse;
import com.example.services.PaymentApiService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class PaymentApiTest extends BaseTest {

    PaymentApiService paymentApiService = new PaymentApiService();

    @Test
    void canCheckPaymentServiceHealth(){
        AssertableResponse assertableResponse = paymentApiService.checkHealth()
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave( bodyJson("jsonSchemas/getHealth.json"));
        assertThat(assertableResponse.asPojo(GetHealthResponse.class).getHealth().size(),is(greaterThanOrEqualTo(1)));

    }


}
