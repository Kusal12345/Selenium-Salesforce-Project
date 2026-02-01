package com.salesforce.pages;

import org.openqa.selenium.By;

public final class Locators {
    private Locators() {}

    // XPath for the Login button on Trailhead's homepage. Kept in a central place for reuse.
    public static final By LOGIN_BUTTON = By.xpath("//a[contains(text(),'Log') or contains(text(),'Login')]");
}
