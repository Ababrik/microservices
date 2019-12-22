package com.socks.ui.tests.utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.Dimension;
import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidWebDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("79.0");
        browser.setCapability("enableVNC", true);

        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://127.0.0.1:4444/wd/hub").toURL(),
                    browser
            );
            driver.manage().window().setSize(new Dimension(1280, 1024));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

}
