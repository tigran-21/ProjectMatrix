package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObjects {
    WebDriver driver;
    By eyeIconState = By.cssSelector("span[role=\"img\"]");
    By checkBoxValue = By.xpath("//label/span[1]");
    By forgotPasswordLink = By.cssSelector("a[href=\"https://passwd.instigatemobile.com/\"]");

    public LoginPageObjects(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEyeIcon() {
        driver.findElement(eyeIconState).click();
    }

    public String getEyeIconState() {
        return driver.findElement(eyeIconState).getAttribute("aria-label");
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    public WebElement getCheckBoxValue() {
        return driver.findElement(checkBoxValue);
    }

}
