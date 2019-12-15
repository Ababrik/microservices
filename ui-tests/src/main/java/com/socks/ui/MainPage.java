package com.socks.ui;

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
        $("#username-modal").sendKeys(username);
        $("#password-modal").sendKeys(password);
        $("#login-modal p button").click();
    }

    public SelenideElement getLoginLink(){
        return $("#login > a");
    }

    public SelenideElement getAlertInvalidLoginCredentials(){
        return $("#login-message>.alert.alert-danger");
    }

}
