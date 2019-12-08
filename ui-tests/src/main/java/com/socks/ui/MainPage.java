package com.socks.ui;

import com.codeborne.selenide.Selenide;

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

}
