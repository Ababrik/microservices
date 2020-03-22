package com.socks.ui.page;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class LoggedUserPage {

    public SelenideElement getLogoutButton() {
        return $("#logout > a");
    }

    public void logOut() {
        $("#logout>a").shouldBe(visible).click();
    }

    public SelenideElement getLoggedUserLink(String firstname, String lastname){
        return $x(String.format("//a[contains(text(), 'Logged in as %s %s')]", firstname, lastname));
    }
}
