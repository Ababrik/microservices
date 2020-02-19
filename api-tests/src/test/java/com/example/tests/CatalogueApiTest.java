package com.example.tests;

import com.example.assertions.AssertableResponse;
import com.example.responses.GetCatalogueSizeResponse;
import com.example.responses.GetTagsResponse;
import com.example.services.CatalogueApiService;
import io.restassured.http.ContentType;
import org.assertj.core.error.AssertionErrorMessagesAggregrator;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static com.example.conditions.Conditions.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

public class CatalogueApiTest extends BaseTest {
    private CatalogueApiService catalogueApiService = new CatalogueApiService();
    @Test
    void canGetCatalogue(){
        catalogueApiService.getCatalogue()
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCatalogueSchema.json"));

    }

    @Test
    void canGetProductById(){
        catalogueApiService.getProductById("a0a4f044-b040-410d-8ead-4de0446aec7e")
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getProduct.json"));
    }

@Test
    void canGetCataloguesSize(){
        AssertableResponse size = catalogueApiService.getCatalogueSize()
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getCatalogueSize.json"));
       assertThat(size.asPojo(GetCatalogueSizeResponse.class).getSize(), is(greaterThanOrEqualTo(1)));
}

@Test
    void canGetTags(){
        AssertableResponse tags = catalogueApiService.getTags()
                .shouldHave(statusCode(200))
//                .shouldHave(contentType(ContentType.JSON))
                .shouldHave(bodyJson("jsonSchemas/getTags.json" ));
        assertThat(tags.asPojo(GetTagsResponse.class).getTags().size(), is(greaterThanOrEqualTo(1)) );
}

}
