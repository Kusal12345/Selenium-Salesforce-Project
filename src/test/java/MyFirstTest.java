import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyFirstTest {
    public static void main(String[] args) {
        
        // 1. Launch Chrome Browser
        WebDriver driver = new ChromeDriver();
        
        // 2. Set a Smart Wait (30 seconds)
        //testing
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // 3. Open Salesforce Trailhead
            driver.get("https://trailhead.salesforce.com/");
            
            // 4. Print the Page Title (The simple line for tracking!)
            System.out.println("The page title is: " + driver.getTitle());

            // 5. Find and Click the Login Button
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Log') or contains(text(),'Login')]")
            ));
            
            loginButton.click();
            System.out.println("Successfully clicked Login button.");

            // Wait 5 seconds so you can see the result
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // 6. Close browser to save your 16GB RAM
            driver.quit();
         // Automation test completed for Salesforce.
         //Just checking push working or not
        }
    }
}