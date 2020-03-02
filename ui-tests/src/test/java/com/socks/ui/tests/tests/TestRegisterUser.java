package com.socks.ui.tests.tests;

import com.codeborne.selenide.Condition;
import com.socks.ui.Page.LoggedUserPage;
import com.socks.ui.Page.MainPage;
import com.socks.ui.Page.utils.UserDetails;
import org.testng.annotations.Test;

import java.util.Map;

public class TestRegisterUser extends BaseUiTest {
UserDetails userDetails = new UserDetails();

    @Test
    void canRegisterUser() {
        System.out.println("TestRegisterUser --- " + Thread.currentThread().getId());
        //given
        Map<String, String> userCreds = userDetails.generateUserCredentials();
        //when
        MainPage.open().
                register(
                        userCreds.get("username"), userCreds.get("firstname"), userCreds.get("lastname"), userCreds.get("email"), userCreds.get("password")
                );
        //then
        at(LoggedUserPage.class).getLoggedUserLink(userCreds.get("firstname"), userCreds.get("lastname")).shouldBe(Condition.visible);
    }



}
