package com.socks.ui.tests.tests;

import com.example.services.UserApiService;
import com.example.utils.UserApiServiceUtils;

import static com.codeborne.selenide.Condition.*;

import com.example.model.UserPayload;
import com.socks.ui.Page.LoggedUserPage;
import com.socks.ui.Page.HomePage;
import org.testng.annotations.Test;


public class LoginTest extends BaseUiTest {
    UserApiServiceUtils userApiServiceUtils = new UserApiServiceUtils();
    UserApiService userApiService = new UserApiService();

    @Test
    public void userCanLoginWithValidCredentials() {
//        given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
//        when
        HomePage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
//
//        then
        at(LoggedUserPage.class).gerLogoutButton().shouldHave(text("Logout"));
    }


    @Test
    public void userCanLogout() {
        //given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
        HomePage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
        //when
        at(LoggedUserPage.class).logOut();
        //then
        at(HomePage.class).getLoginLink().shouldHave(text("Login"));
    }

    @Test
    public void userCannotLoginWithInvalidUsername() {
        // given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
        // when
        HomePage.open().logIn(userPayload.getUsername() + "1", userPayload.getPassword());
        // then
        at(HomePage.class).getAlertInvalidLoginCredentials().shouldHave(text("Invalid login credentials."));
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        // given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
        // when
        HomePage.open().logIn(userPayload.getUsername() + "1", userPayload.getPassword() + "1");
        //then
        at(HomePage.class).getAlertInvalidLoginCredentials().shouldHave(text("Invalid login credentials."));
    }


}
