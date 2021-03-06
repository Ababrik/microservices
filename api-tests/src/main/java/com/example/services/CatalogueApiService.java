package com.example.services;

import com.example.assertions.AssertableResponse;

import java.util.ArrayList;

import static java.util.Arrays.asList;


public class CatalogueApiService extends ApiService {

    public AssertableResponse getCatalogue() {
        return new AssertableResponse(setUp()
                .when()
                .get("/catalogue"));
    }

    public AssertableResponse getProductById(String id) {
        return new AssertableResponse(setUp()
                .when()
                .get("/catalogue/" + id));
    }

    public AssertableResponse getCatalogueSize() {
        return new AssertableResponse(setUp()
                .when()
                .get(" /catalogue/size"));
    }

    public AssertableResponse getTags() {
        return new AssertableResponse(setUp()
                .when()
                .get("/tags"));
    }

    public AssertableResponse deleteCatalogItem(String itemId) {
        return new AssertableResponse(setUp()
                .when()
                .delete("/catalogue/" + itemId));
    }

}
