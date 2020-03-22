package com.socks.ui.page;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.socks.ui.elements.DropdownMenu;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    public static HomePage open() {
        Selenide.open("/");
        return new HomePage();
    }


    public void logIn(String username, String password) {
        $("#login > a").click();
        SelenideElement uName = $("#username-modal");
        Selenide.executeJavaScript("arguments[0].value='" + username + "';", uName);
        SelenideElement passw = $("#password-modal");
        Selenide.executeJavaScript("arguments[0].value='" + password + "';", passw);
        $("#login-modal p button").click();
    }

    public SelenideElement getLoginLink() {
        return $("#login > a");
    }

    public SelenideElement getAlertInvalidLoginCredentials() {
        return $("#login-message>.alert.alert-danger");
    }

    public void register(String username, String firstName, String lastName, String email, String password) {
        $("#register").click();
        $("#register-username-modal").sendKeys(username);
        $("#register-first-modal").sendKeys(firstName);
        $("#register-last-modal").sendKeys(lastName);
        $("#register-email-modal").sendKeys(email);
        $("#register-password-modal").sendKeys(password);
        $("button[onclick='return register()']").click();
    }

    public CataloguePage openCatalogue() {
        $("#tabCatalogue a[href='category.html']").click();
        return new CataloguePage();
    }

    public CataloguePage filterCatalogProducts(String option) {
        DropdownMenu dropdownMenu = new DropdownMenu().open();
        return dropdownMenu.selectOption(option);
    }


}
