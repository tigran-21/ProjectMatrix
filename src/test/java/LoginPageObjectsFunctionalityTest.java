import constants.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoginPageObjects;
import pages.SubmittedPage;

public class LoginPageObjectsFunctionalityTest {

    private static WebDriver driver = null;

    @BeforeTest
    public void setUpTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://projectmatrix.instigatemobile.com/login");
    }

    @Test(priority = 1, description = "Verify eye icon functionality")
    public void EyeIconFunctionalityTest() {
        LoginPageObjects loginPageObjects = new LoginPageObjects(driver);

        String actualEyeIconState = loginPageObjects.getEyeIconState();


        if (actualEyeIconState.equals("eye")) {
            loginPageObjects.clickEyeIcon();
            Assert.assertEquals("eye-invisible", loginPageObjects.getEyeIconState(),
                    "Eye icon did not toggle password visibility");
        } else {
            loginPageObjects.clickEyeIcon();
            Assert.assertEquals("eye", loginPageObjects.getEyeIconState(),
                    "Eye icon did not toggle password visibility");
        }

    }

    @Test(priority = 2, description = "Verify that the checkbox can be selected and deselected.")
    public void checkBoxSelectAndDeselectTest() {
        //Setup
        LoginPageObjects loginPageObjects = new LoginPageObjects(driver);
        WebElement checkBox = loginPageObjects.getCheckBoxValue();
        String currentValue;
        String expectedValue;

        // Verify initial state
        currentValue = checkBox.getAttribute("class");
        expectedValue= "ant-checkbox";
        Assert.assertEquals(expectedValue, currentValue, "Checkbox initial state is incorrect.");

        // Verify checkbox can be selected
        checkBox.click();
        currentValue = checkBox.getAttribute("class");
        expectedValue = "ant-checkbox ant-checkbox-checked";
        Assert.assertEquals(expectedValue, currentValue, "Checkbox was not selected.");

        // Verify checkbox can be deselected
        checkBox.click();
        currentValue = checkBox.getAttribute("class");
        expectedValue= "ant-checkbox";
        Assert.assertEquals(expectedValue, currentValue, "Checkbox was not deselected.");
    }


    @Test(priority = 3, description = "verify \"Forgot Password?\" link functionality")
    public void forgotPasswordLinkTest() {
        LoginPageObjects loginPageObjects = new LoginPageObjects(driver);

        loginPageObjects.clickForgotPasswordLink();
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://passwd.instigatemobile.com/";
        Assert.assertEquals(expectedURL, currentURL, "\"Forgot your password?\" link is not working correct");
    }

    @AfterTest
    public void tearDownTest() {
        if (driver != null) {
            driver.quit();
            System.out.println("Test completed successfully");
        }
    }
}
