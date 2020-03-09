package com.socks.ui.tests.tests;


import com.codeborne.selenide.Condition;
import com.example.services.CatalogueApiService;
import com.socks.ui.Page.CataloguePage;
import com.socks.ui.Page.MainPage;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CatalogueTest extends BaseUiTest {

    CatalogueApiService catalogueApiService = new CatalogueApiService();

    @Test
    void canOpenCataloguePage() {
        MainPage.open().openCatalogue();
        assertThat(at(CataloguePage.class).getUrl(), is("http://51.15.240.40/category.html"));
    }

    @Test
    void canFilterViaSidebarMenu() {
        MainPage.open().openCatalogue().filterOnSidebarMenu("blue");
        at(CataloguePage.class).getFilerOption("blue").shouldBe(Condition.checked);
    }

    @Test
    void canClearSelectedFilters() {
        catalogueApiService.getCatalogueWithQueryParameters("red", "sport");
        MainPage.open().openCatalogue().clearFilters();
        at(CataloguePage.class).getFilerOption("red").shouldNotBe(Condition.checked);
        at(CataloguePage.class).getFilerOption("sport").shouldNotBe(Condition.checked);

    }

}
