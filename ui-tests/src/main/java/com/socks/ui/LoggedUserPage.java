package com.socks.ui;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LoggedUserPage {

    public SelenideElement gerLogoutButton() {
        return $("#logout > a");
    }

    public void logOut() {
        $("#logout>a").shouldBe(Condition.visible).click();
    }
}
