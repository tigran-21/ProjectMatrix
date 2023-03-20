package pages;

import constants.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class LoginPage {

    Constant constant = new Constant();
    SubmittedPage submittedPage = new SubmittedPage();
    WebDriver driver;
    By userLogin = By.xpath("//input[@name=\"username\"]");
    By userPasswords = By.xpath("//input[@name=\"password\"]");
    By loginButton = By.xpath("//button[@type= \"submit\"]");

    By invalidCredentials = By.cssSelector("p[class*=\"kbjrVp\"]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserLogin(String text) {
        driver.findElement(userLogin).sendKeys(text);
    }

    public void setUserPasswords(String text) {
        driver.findElement(userPasswords).sendKeys(text);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).sendKeys(Keys.RETURN);
    }

    public void waitForErrorMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constant.WAIT_DURATION));
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentials));
    }
    public String InvalidCredentialsErrorMessage(){
        return driver.findElement(invalidCredentials).getText();

    }

    public void waitForLogin(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(constant.WAIT_DURATION));
        wait.until(ExpectedConditions.visibilityOfElementLocated(submittedPage.mainTitle));
    }

}