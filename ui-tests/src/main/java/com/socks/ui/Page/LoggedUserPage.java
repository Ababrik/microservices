package com.socks.ui.Page;

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

    public SelenideElement getLoggedUserLink(String firstname, String lastname){
        return $x(String.format("//a[contains(text(), 'Logged in as %s %s')]", firstname, lastname));
    }
}
