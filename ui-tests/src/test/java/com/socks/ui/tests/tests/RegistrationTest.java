package com.socks.ui.tests.tests;

import com.codeborne.selenide.Condition;
import com.socks.ui.page.LoggedUserPage;
import com.socks.ui.page.HomePage;
import org.testng.annotations.Test;

import java.util.Map;

public class RegistrationTest extends BaseUiTest {


    @Test
    void canRegisterUser() {
        //given
        Map<String, String> userCreds = userDetails.generateUserCredentials();
        //when
        HomePage.open().
                register(
                        userCreds.get("username"), userCreds.get("firstname"), userCreds.get("lastname"),
                        userCreds.get("email"), userCreds.get("password")
                );
        //then
        at(LoggedUserPage.class).getLoggedUserLink(userCreds.get("firstname"), userCreds.get("lastname")).shouldBe(Condition.visible);
    }


}
