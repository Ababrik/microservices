package com.socks.ui.Page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;

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


}
