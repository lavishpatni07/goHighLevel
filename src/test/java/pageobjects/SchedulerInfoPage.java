package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SchedulerInfoPage {
    WebDriver driver;
    String heading = "h4.text-info";
    String firstName = "first_name";
    String lastName = "last_name";
    String phone = "phone";
    String email = "//*[@name='email']";
    String scheduleButton = "button.btn.btn-schedule";
    String selectedFlag = "div.iti__selected-flag";
    String  message = "//textarea[@name='calendar_notes']";
    public SchedulerInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getFirstName() {
        return driver.findElement(By.id(firstName));
    }

    public WebElement getLastName() {
        return driver.findElement(By.id(lastName));
    }

    public WebElement getPhone() {
        WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selectedFlag)));
        return driver.findElement(By.id(phone));
    }

    public WebElement getMessage() {
        return driver.findElement(By.xpath(message));
    }

    public WebElement getEmail() {
        return driver.findElement(By.xpath(email));
    }

    public WebElement getScheduleButton() {
        return driver.findElement(By.cssSelector(scheduleButton));
    }

    public String getHeading() {
        return driver.findElement(By.cssSelector(heading)).getText();
    }

    public ConfirmationPage scheduleFor(String fName, String lName, String phoneNumber, String email, String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(heading))));
        getFirstName().sendKeys(fName);
        getLastName().sendKeys(lName);
        getPhone().sendKeys(phoneNumber+ Keys.TAB);
        getEmail().sendKeys(email);
        getMessage().sendKeys(message);
        getScheduleButton().click();
        return new ConfirmationPage(driver);
    }
}
