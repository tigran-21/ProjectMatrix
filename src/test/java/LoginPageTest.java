import constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;


public class LoginPageTest {
    private static WebDriver driver = null;

    @BeforeTest
    public void setUpTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://projectmatrix.instigatemobile.com/login");

    }

    @Test(priority = 1, description = "Verify that the user can log on with a valid username and valid password.")
    public void LoginTest() {

        String username = System.getProperty("username");
        String password = System.getProperty("password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserLogin(username);
        loginPage.setUserPasswords(password);
        loginPage.clickLoginButton();

        Constant user = new Constant();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(user.WAIT_DURATION));
        WebElement title =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='All Efforts']")));

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://projectmatrix.instigatemobile.com/timesheet/allEfforts/submitted";

        Assert.assertEquals(actualURL, expectedURL, "The user is not logged in.");

    }

    @AfterTest
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test completed successfully");
        }
    }
}
