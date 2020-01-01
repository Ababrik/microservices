package com.socks.ui.tests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.ProjectConfig;
import com.example.model.UserPayload;
import com.example.services.UserApiService;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class BaseUiTest {
    protected final Faker faker = new Faker();
    private UserApiService userApiService = new UserApiService();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
        Configuration.browser = "chrome";

        //run tests via selenoid
//        Configuration.browser = "com.socks.ui.tests.utils.SelenoidWebDriverProvider";
//        Configuration.driverManagerEnabled = false;

        Configuration.baseUrl = "http://159.65.243.118";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterMethod
    public void cleanCookies() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.refresh();
    }


    protected UserPayload createNewUser() {
        String userName = (faker.name().username());
        UserPayload userPayload = new UserPayload()
                .setUsername(userName)
                .setPassword(faker.numerify("a#b##b#a"))
                .setEmail(userName + "@example.com");
        userApiService.registerUser(userPayload);
        return userPayload;
    }

    protected Map<String, String> generateUserCredentials() {
        Map<String, String> userCreds = new HashMap<>();
        String userName = (faker.name().username());
        userCreds.put("username", userName);
        String firstName = faker.name().firstName();
        userCreds.put("firstname", firstName);
        String lastName = (faker.name().lastName());
        userCreds.put("lastname", lastName);
        String email = userName + "@example.com";
        userCreds.put("email", email);
        String passw = (faker.numerify("a#b##b#a"));
        userCreds.put("password", passw);
        return userCreds;
    }

    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }

}
