package com.salesforce.tests;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.salesforce.pages.HomePage;

public class MyFirstTest {
    public static void main(String[] args) {
        System.out.println("Starting simple PASS-style test run...");
        
        // 1. Launch Chrome Browser
        WebDriver driver = new ChromeDriver();
        
        // 2. Create HomePage with 30s wait
        HomePage home = new HomePage(driver, Duration.ofSeconds(30));

        try {
            // 3. Open Salesforce Trailhead using POM
            home.open();
            System.out.println("[PASS] Opened the home page");
            
            // 4. Print the Page Title
            System.out.println("The page title is: " + home.getTitle());

            // 5. Click Login via the page object
            home.clickLogin();
            System.out.println("[PASS] Successfully clicked Login button.");

            // Wait 2 seconds so you can see the result
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // 6. Close browser
            driver.quit();
         // Automation test completed for Salesforce.
        }
        System.out.println("Simple PASS-style test run completed.");
    }
}