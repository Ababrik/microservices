package com.socks.ui.Page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public static MainPage open() {
        Selenide.open("/");
        return new MainPage();
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

    public void register(String username, String firstName, String lastName, String email, String password){
        $("#register").click();
        $("#register-username-modal").sendKeys(username);
        $("#register-first-modal").sendKeys(firstName);
        $("#register-last-modal").sendKeys(lastName);
        $("#register-email-modal").sendKeys(email);
        $("#register-password-modal").sendKeys(password);
        $("button[onclick='return register()']").click();
    }

    public CataloguePage openCatalogue(){
        $("#tabCatalogue a[href='category.html']").click();
        return new CataloguePage();
    }


}
