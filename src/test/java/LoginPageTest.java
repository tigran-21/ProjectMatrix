import java.util.Scanner;

import constants.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SubmittedPage;


public class LoginPageTest {
    WebDriver driver = null;

    static String username = System.getProperty("username");
    static String password = System.getProperty("password");
    @BeforeTest
    public void setUpTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");


        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://projectmatrix.instigatemobile.com/login");
    }

    @Test(priority = 1, description = "Verify the user is unable to login with a valid username and invalid password.")
    public void InvalidPasswordTest() {

        driver.navigate().refresh();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUserLogin(username);
        loginPage.setUserPasswords("password");
        loginPage.clickSignInButton();
        loginPage.waitForErrorMessage();

        String actualErrorMessage = loginPage.InvalidCredentialsErrorMessage();
        String expectedErrorMessage = "Invalid username or password, please verify them and retry";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Something went wrong");

    }

    @Test(priority = 2, description = "Verify the user is unable to login with a invalid username and and valid password.")
    public void InvalidUsernameTest() {

        driver.navigate().refresh();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUserLogin("username");
        loginPage.setUserPasswords(password);
        loginPage.clickSignInButton();
        loginPage.waitForErrorMessage();

        String actualErrorMessage = loginPage.InvalidCredentialsErrorMessage();
        String expectedErrorMessage = "Invalid username or password, please verify them and retry";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Something went wrong");
    }

    @Test(priority = 3, description = "Verify that the user can log on with a valid username and valid password.")
    public void LoginTest() {

        driver.navigate().refresh();
        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUserLogin(username);
        loginPage.setUserPasswords(password);
        loginPage.clickSignInButton();
        loginPage.waitForLogin();

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
