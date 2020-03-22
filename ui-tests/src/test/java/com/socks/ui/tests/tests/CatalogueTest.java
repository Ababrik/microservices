package com.socks.ui.tests.tests;


import static com.codeborne.selenide.Condition.*;

import com.socks.ui.page.CataloguePage;
import com.socks.ui.page.HomePage;
import com.socks.ui.page.ProductPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;

public class CatalogueTest extends BaseUiTest {


    @Test
    void canOpenCataloguePageFromHomePage() {
        HomePage.open().openCatalogue();
        assertThat(at(CataloguePage.class).getUrl(), containsString("/category.html"));
    }

    @Test
    void canFilterViaSidebarMenu() {
        CataloguePage.open().filterOnSidebarMenu("magic");
        at(CataloguePage.class).getFilerOption("magic").shouldBe(checked);
    }

    @Test
    void canClearSelectedFilters() {
        CataloguePage.open().filterOnSidebarMenu("red");
        at(CataloguePage.class).clearFilters();
        at(CataloguePage.class).getFilerOption("red").shouldNotBe(checked);
    }

    @Test
    void canSetPagination() {
        CataloguePage.open().setProductsQuantityToDisplay("3");
        assertThat(at(CataloguePage.class).getAllDisplayedPerPageProducts().size(), is(3));
    }

    @Test
    void canSortProducts() {
        CataloguePage.open().sortProducts("Name");
        at(CataloguePage.class).getSortOption().shouldBe(selected);
    }

    @Test
    void canOpenProductPage() {
        CataloguePage.open().openProductPage();
        assertThat(at(ProductPage.class).getUrl(), containsString(("/detail.html?id=")));
        at(ProductPage.class).getBreadcrumb().shouldBe(visible);
    }

    @Test
    void canReturnToCatalogFromProductPageViaBreadcrumb() {
        CataloguePage.open().openProductPage().returnToCatalogPageViaBreadcrumb();
        assertThat(at(CataloguePage.class).getUrl(), containsString("/category.html"));
    }

    @Test
    void canFilterViaDropdownMenuFromHomePage() {
        HomePage.open().filterCatalogProducts("formal");
        at(CataloguePage.class).getFilerOption("formal").shouldBe(selected);
    }

    @Test
    void canFilterViaDropdownMenuFromCataloguePage() {
        CataloguePage.open().filterProductsViaDropdownMenu("brown")
                .getFilerOption("brown").shouldBe(selected);

    }

}
