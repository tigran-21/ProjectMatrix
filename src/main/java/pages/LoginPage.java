package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    By userLogin = By.xpath("//input[@name=\"username\"]");
    By userPasswords = By.xpath("//input[@name=\"password\"]");
    By loginButton = By.xpath("//button[@type= \"submit\"]");

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
}