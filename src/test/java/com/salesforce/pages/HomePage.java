package com.salesforce.pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String url = "https://trailhead.salesforce.com/";

    public HomePage(WebDriver driver, Duration timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, timeout);
    }

    public void open() {
        driver.get(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickLogin() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(Locators.LOGIN_BUTTON));
        loginButton.click();
    }
}
