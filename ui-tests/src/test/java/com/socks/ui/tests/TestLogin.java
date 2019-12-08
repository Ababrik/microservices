package com.socks.ui.tests;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.services.UserApiService;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;


public class TestLogin extends BaseUiTest {


    @Test
    public void userCanLoginWithValidCredentials() {
//        given
        UserPayload userPayload = createNewUser();
//        when
        MainPage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
//        then
        at(LoggedUserPage.class).logoutButton.shouldHave(text("Logout"));
    }
}
