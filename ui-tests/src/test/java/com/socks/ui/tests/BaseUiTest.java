package com.socks.ui.tests;

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
import org.testng.annotations.BeforeClass;

public class BaseUiTest  {
    protected final Faker faker = new Faker();
    private UserApiService userApiService = new UserApiService();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://159.65.243.118";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
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

    protected  <T> T at(Class<T> pageClass){
        return Selenide.page(pageClass);
    }

}
