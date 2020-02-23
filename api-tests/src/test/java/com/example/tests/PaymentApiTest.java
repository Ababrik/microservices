package com.example.tests;
import static com.example.conditions.Conditions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import com.example.assertions.AssertableResponse;
import com.example.responses.GetHealthResponse;
import com.example.services.PaymentApiService;
import com.example.services.UserApiService;
import com.example.utils.UserApiServiceUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PaymentApiTest extends BaseTest {

    PaymentApiService paymentApiService = new PaymentApiService();
    UserApiService userApiService = new UserApiService();
    UserApiServiceUtils userApiServiceUtils = new UserApiServiceUtils();

    @Test
    void canCheckPaymentServiceHealth(){
        AssertableResponse assertableResponse = paymentApiService.checkHealth()
//                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave( bodyJson("jsonSchemas/getHealth.json"));
        assertThat(assertableResponse.asPojo(GetHealthResponse.class).getHealth().size(),is(greaterThanOrEqualTo(1)));
    }

    @Test
    void canDoPaymentAuthorisation(){
        Map<String, String> cookies = userApiService.registerUser(userApiServiceUtils.generateUserDetails()).getCookies();
        paymentApiService.doPaymentAuthorisation(cookies)
                .shouldHave(statusCode(200))
                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyField("authorised", is(true)));

    }

}
