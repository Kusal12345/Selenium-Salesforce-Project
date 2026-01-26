import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyFirstTest {
    public static void main(String[] args) {
        // 1. Launch Chrome
        WebDriver driver = new ChromeDriver();
        
        // Wait up to 30 seconds for elements to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // 2. Navigate to Trailhead
            driver.get("https://trailhead.salesforce.com/");
            System.out.println("Opening Trailhead on Chrome...");

            // 3. Find the 'Login' button
            // Salesforce often uses 'Login' or 'Log In'. This XPath finds both.
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Log') or contains(text(),'Login')]")
            ));
            
            // 4. Click the button
            loginButton.click();
            System.out.println("Clicked the Login button!");

            // Stay on the login screen for 5 seconds
            Thread.sleep(5000);

        } catch (Exception e) {
            System.out.println("Error: Could not find or click the login button.");
            e.printStackTrace();
        } finally {
            // Close the browser to save your 16GB RAM
            driver.quit(); 
        }
    }
}