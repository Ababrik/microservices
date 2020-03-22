package com.socks.ui.elements;

import com.codeborne.selenide.SelenideElement;
import com.socks.ui.page.CataloguePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class DropdownMenu {

    public DropdownMenu open(){
        $("#tabCatalogue").hover().$("#tabCatalogue > .dropdown-menu").shouldBe(visible);
        return this;
    }

    private SelenideElement findOption(String optionName){
        return $(String.format("a[href^='category.html?tags=%s']", optionName)).shouldBe(text(optionName));

    }

    public CataloguePage selectOption(String optionName){
        SelenideElement option = findOption(optionName);
        actions().moveToElement(option).click(option).perform();
        return new CataloguePage();
    }

}
