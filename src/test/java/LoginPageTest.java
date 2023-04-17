import Helper.CreateUser;
import Trial.RunTest;
import constants.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPageTest {
    WebDriver driver = null;

    static String[] userCredentials = CreateUser.createUser();
//    static String username = null;
//    static String password = null;
    static String username = System.getProperty("username");
    static String password = System.getProperty("password");

    @BeforeMethod
    public void setUpTest() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        Constant constant = new Constant();

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(constant.LOGIN_URL);


        try {
            // Use the username and password variables for authentication
            if (username == null) {  // Get username from ".user.json" file
                username = userCredentials[0];
                System.out.println(username);
            }
            if (password == null) {  // Get password from ".user.json" file
                password = userCredentials[1];
                System.out.println(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(priority = 1, description = "Verify the user is unable to login with a valid username and invalid password.")
    public void InvalidPasswordTest() {

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

        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUserLogin(username);
        loginPage.setUserPasswords(password);
        loginPage.clickSignInButton();
        loginPage.waitForLogin();


        Constant constant = new Constant();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = constant.SUBMITTED_URL;

        Assert.assertEquals(actualURL, expectedURL, "The user is not logged in.");
    }

    @AfterMethod
    public void tearDownTest() throws IOException, NoSuchMethodException {

        if (driver != null) {
            driver.quit();
            System.out.println("Test completed successfully");
        }
    }
}
