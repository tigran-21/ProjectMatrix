package pages;

import constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    Constant constant = new Constant();
    SubmittedPage submittedPage = new SubmittedPage();
    WebDriver driver;

    By userLogin = By.cssSelector("input[name=\"username\"]");
    By userPasswords = By.cssSelector("input[name=\"password\"]");
    By signInButton = By.cssSelector("button[type= \"submit\"]");
    By invalidCredentials = By.cssSelector("p[class*=\"kbjrVp\"]");


    //Set user login
    public void setUserLogin(String text) {

        driver.findElement(userLogin).sendKeys(text);
    }
    //Set user password
    public void setUserPasswords(String text) {

        driver.findElement(userPasswords).sendKeys(text);
    }
    //Click "Sign in" button
    public void clickSignInButton() {

        driver.findElement(signInButton).sendKeys(Keys.RETURN);
    }

    public void waitForErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constant.WAIT_DURATION));
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentials));
    }

    public String InvalidCredentialsErrorMessage() {
        return driver.findElement(invalidCredentials).getText();
    }

    public void waitForLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constant.WAIT_DURATION));
        wait.until(ExpectedConditions.visibilityOfElementLocated(submittedPage.mainTitle));
    }

}