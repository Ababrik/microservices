package com.socks.ui.tests.tests;

import com.codeborne.selenide.Condition;
import com.socks.ui.LoggedUserPage;
import com.socks.ui.MainPage;
import org.testng.annotations.Test;

import java.util.Map;

public class TestRegisterUser extends BaseUiTest {


    @Test
    void canRegisterUser() {
        System.out.println("TestRegisterUser --- " + Thread.currentThread().getId());
        //given
        Map<String, String> userCreds = generateUserCredentials();
        //when
        MainPage.open().
                register(
                        userCreds.get("username"), userCreds.get("firstname"), userCreds.get("lastname"), userCreds.get("email"), userCreds.get("password")
                );
        //then
        at(LoggedUserPage.class).getLoggedUserLink(userCreds.get("firstname"), userCreds.get("lastname")).shouldBe(Condition.visible);
    }



}
