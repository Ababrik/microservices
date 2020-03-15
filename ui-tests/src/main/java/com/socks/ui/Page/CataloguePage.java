package com.socks.ui.Page;

import com.codeborne.selenide.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CataloguePage {

    public static CataloguePage open() {
        Selenide.open("/category.html");
        return new CataloguePage();
    }

    public String getUrl() {
        return WebDriverRunner.url();
    }

    private void selectFilterOption(String optionName) {
        $(String.format("input[value='%s']", optionName)).click();
    }

    private void submitFiltersForm() {
        $("#filters-form > a").click();
    }

    public void clearFilters() {
        $("a[onclick='resetTags()']").click();
    }

    public void filterOnSidebarMenu(String optionName) {
        selectFilterOption(optionName);
        submitFiltersForm();
    }

    public SelenideElement getFilerOption(String optionName) {
        return $(String.format("#filters-form input[value='%s']", optionName));
    }

    public void setProductsQuantityToDisplay(String pagination) {
        $(String.format("a[onclick='setNewPageSize(%s)']", pagination)).click();

    }

    public ElementsCollection getAllDisplayedPerPageProducts() {
        return $$("div#products div.product").shouldHave(CollectionCondition.sizeNotEqual(0));
    }

    public void sortProducts(String optionName) {
        $("select[name='sort-by']").selectOptionContainingText(optionName);
    }

    public SelenideElement getSortOption(){
        return $("select[name='sort-by']");
    }

    public ProductPage openProductPage(){
        $("a[href^='detail.html?id=']").click();
        return new ProductPage();
    }


}
