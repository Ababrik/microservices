package com.socks.ui.tests.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.ProjectConfig;
import com.example.services.UserApiService;
import com.example.utils.UserApiServiceUtils;
import com.socks.ui.utils.UserDetails;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.*;



public class BaseUiTest {
    protected UserApiServiceUtils userApiServiceUtils = new UserApiServiceUtils();
    protected UserApiService userApiService = new UserApiService();
    protected UserDetails userDetails = new UserDetails();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ConfigFactory.create(ProjectConfig.class).apiPath();
        Configuration.browser = "chrome";

        //run tests via selenoid
//        Configuration.browser = "com.socks.ui.tests.utils.SelenoidWebDriverProvider";
//        Configuration.driverManagerEnabled = false;

        Configuration.baseUrl = "http://51.15.240.40";
        //        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterMethod
    public void cleanCookies() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Configuration.holdBrowserOpen=true;
//        Selenide.refresh();
    }



    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }

}
