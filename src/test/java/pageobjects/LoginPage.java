package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.HomePage;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    private String email = "email";
    private String password = "password";
    private String signInButton = "button.hl-btn.justify-center.w-full";
    private String heading = "h2.heading2";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmail() {
        return driver.findElement(By.id(email));
    }

    public WebElement getPassword() {
        return driver.findElement(By.id(password));
    }

    public WebElement getSignInButton() {
        return driver.findElement(By.cssSelector(signInButton));
    }

    public HomePage Login(String email, String password){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        getEmail().sendKeys(email);
        getPassword().sendKeys(password);
        getSignInButton().click();
        return new HomePage(driver);
    }
}
