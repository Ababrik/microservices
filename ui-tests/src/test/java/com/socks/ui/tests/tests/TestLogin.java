package com.socks.ui.tests.tests;

import com.example.utils.UserApiServiceUtils;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.example.model.UserPayload;
import com.socks.ui.Page.LoggedUserPage;
import com.socks.ui.Page.MainPage;
import org.testng.annotations.Test;


public class TestLogin extends BaseUiTest {
    UserApiServiceUtils userApiServiceUtils = new UserApiServiceUtils();

    @Test
    public void userCanLoginWithValidCredentials() {
        System.out.println("TestLogin --- " + Thread.currentThread().getId());
//        given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
//        when
        MainPage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
//        then
        at(LoggedUserPage.class).gerLogoutButton().shouldHave(text("Logout"));
    }


    @Test
    public void userCanLogout() {
        System.out.println("TestLogin --- " + Thread.currentThread().getId());
        //given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        MainPage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
        //when
        at(LoggedUserPage.class).logOut();
        //then
        at(MainPage.class).getLoginLink().shouldHave(Condition.text("Login"));
    }

    @Test
    public void userCannotLoginWithInvalidUsername() {
        System.out.println("TestLogin --- " + Thread.currentThread().getId());
        // given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        // when
        MainPage.open().logIn(userPayload.getUsername() + "1", userPayload.getPassword());
        // then
        at(MainPage.class).getAlertInvalidLoginCredentials().shouldHave(Condition.text("Invalid login credentials."));
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        System.out.println("TestLogin --- " + Thread.currentThread().getId());
        // given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        // when
        MainPage.open().logIn(userPayload.getUsername() + "1", userPayload.getPassword() + "1");
        //then
        at(MainPage.class).getAlertInvalidLoginCredentials().shouldHave(Condition.text("Invalid login credentials."));
    }


}
