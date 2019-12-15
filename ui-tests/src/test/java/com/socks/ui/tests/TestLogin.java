package com.socks.ui.tests;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.example.model.UserPayload;
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
        at(LoggedUserPage.class).gerLogoutButton().shouldHave(text("Logout"));
    }


    @Test
    public void userCanLogout() {
        //given
        UserPayload userPayload = createNewUser();
        MainPage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
        //when
        at(LoggedUserPage.class).logOut();
        //then
        at(MainPage.class).getLoginLink().shouldHave(Condition.text("Login"));
    }

    @Test
    public void userCannotLoginWithInvalidUsername() {
        // given
        UserPayload userPayload = createNewUser();
        // when
        MainPage.open().logIn(userPayload.getUsername() + "1", userPayload.getPassword());
        // then
        at(MainPage.class).getAlertInvalidLoginCredentials().shouldHave(Condition.text("Invalid login credentials."));
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        // given
        UserPayload userPayload = createNewUser();
        // when
        MainPage.open().logIn(userPayload.getUsername() + "1", userPayload.getPassword() + "1");
        //then
        at(MainPage.class).getAlertInvalidLoginCredentials().shouldHave(Condition.text("Invalid login credentials."));
    }


}
