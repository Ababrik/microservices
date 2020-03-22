package com.socks.ui.tests.tests;
import static com.codeborne.selenide.Condition.*;

import com.example.model.UserPayload;
import com.socks.ui.page.LoggedUserPage;
import com.socks.ui.page.HomePage;
import org.testng.annotations.Test;


public class LoginTest extends BaseUiTest {


    @Test
    public void userCanLoginWithValidCredentials() {
//        given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
//        when
        HomePage.open().logIn(userPayload.getUsername(), userPayload.getPassword());
//
//        then
        at(LoggedUserPage.class).getLogoutButton().shouldHave(text("Logout"));
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
        HomePage.open().logIn(userPayload.getUsername() + System.currentTimeMillis(), userPayload.getPassword());
        // then
        at(HomePage.class).getAlertInvalidLoginCredentials().shouldHave(text("Invalid login credentials."));
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        // given
        UserPayload userPayload = userApiServiceUtils.generateUserDetails();
        userApiService.registerUser(userPayload);
        // when
        HomePage.open().logIn(userPayload.getUsername(), userPayload.getPassword() + System.currentTimeMillis());
        //then
        at(HomePage.class).getAlertInvalidLoginCredentials().shouldHave(text("Invalid login credentials."));
    }


}
