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

            // 3a. Validate the current URL is the Trailhead homepage
            String openedUrl = driver.getCurrentUrl();
            if (openedUrl == null || !openedUrl.toLowerCase().contains("trailhead.salesforce.com")) {
                throw new AssertionError("Did not open the Trailhead homepage. Current URL: " + openedUrl);
            }
            System.out.println("[PASS] Opened the expected Trailhead URL: " + openedUrl);

            // 3b. Validate the page title contains expected text
            String title = home.getTitle();
            if (title == null || title.isEmpty()) {
                throw new AssertionError("Page title is empty or null");
            }
            if (!title.toLowerCase().contains("trailhead")) {
                throw new AssertionError("Unexpected page title (does not contain 'Trailhead'): " + title);
            }
            System.out.println("[PASS] Page title validated: " + title);
            
            // 4. Remember current URL and click Login via the page object
            String beforeUrl = driver.getCurrentUrl();
            home.clickLogin();
            System.out.println("Clicked Login button; waiting for navigation...");

            // 5. Wait briefly for navigation to occur (up to 10s)
            long waitUntil = System.currentTimeMillis() + 10_000;
            boolean urlChanged = false;
            String afterUrl = beforeUrl;
            while (System.currentTimeMillis() < waitUntil) {
                afterUrl = driver.getCurrentUrl();
                if (!afterUrl.equals(beforeUrl)) {
                    urlChanged = true;
                    break;
                }
                try { Thread.sleep(200); } catch (InterruptedException ie) { /* ignore */ }
            }

            // 6. Validate login navigation
            if (!urlChanged) {
                throw new AssertionError("URL did not change after clicking Login. Still: " + afterUrl);
            }

            // Heuristics: check if URL or title indicates a login/signin page
            String afterLower = afterUrl.toLowerCase();
            boolean looksLikeLogin = afterLower.contains("login") || afterLower.contains("signin") || afterLower.contains("auth") || afterLower.contains("sforce") || afterLower.contains("identity");
            if (looksLikeLogin) {
                System.out.println("[PASS] Navigation after click looks like a login page: " + afterUrl);
            } else {
                // Not a strict failure: we changed pages but couldn't detect 'login' keywords.
                System.out.println("[WARN] URL changed after clicking login but did not match common login keywords: " + afterUrl);
            }

            // Wait 1 second so you can see the result
            Thread.sleep(1000);

        } catch (AssertionError ae) {
            System.out.println("[FAIL] Assertion failed: " + ae.getMessage());
            throw ae; // rethrow so failures are visible to CI/runners
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // 7. Close browser and validate it closed
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Warning: driver.quit() threw: " + e.getMessage());
            }

            // Confirm the session is closed by attempting a simple operation that should fail
            try {
                // If the driver still responds, that's unexpected; getTitle should throw after quit
                String postQuitTitle = driver.getTitle();
                // If we get here without exception, treat as failure
                throw new AssertionError("Browser session still active after quit (unexpected). Title after quit: " + postQuitTitle);
            } catch (AssertionError ae) {
                // rethrow assertion errors so caller sees failure
                System.out.println("[FAIL] " + ae.getMessage());
                throw ae;
            } catch (Exception expected) {
                // Expected: an exception indicates the session was closed
                System.out.println("[PASS] Browser session closed (driver threw after quit): " + expected.getClass().getSimpleName());
            }
         // Automation test completed for Salesforce.
        }
        System.out.println("Simple PASS-style test run completed.");
    }
}