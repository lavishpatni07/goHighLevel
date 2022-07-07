package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    private String logo = "img.object-contain.agency-logo";
    private String calendarMenu = "//a[@meta='calendars']";
    private String appointments = "tb_apppontment-tab";
    private String requestedTime = "//span[@title='%s']/ancestor::tr/td[3]/div";
    private String avatar = "div.hl_header--controls a div.avatar div.avatar_img";
    private String logout = "div.hl_header--controls  a.dropdown-item";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getLogo() {
        return driver.findElement(By.cssSelector(logo));
    }

    public WebElement getCalendarMenu() {
        return driver.findElement(By.xpath(calendarMenu));
    }

    public WebElement getAppointments() {
        return driver.findElement(By.id(appointments));
    }

    public WebElement getAvatar() {
        return driver.findElement(By.cssSelector(avatar));
    }

    public WebElement getLogout() {
        return driver.findElement(By.cssSelector(logout));
    }

    public WebElement getRequestedTime(String message) {
        String locator = String.format(requestedTime, message);
        System.out.println("Row Locator: "+locator);
        return driver.findElement(By.xpath(locator));
    }

    public String checkScheduledTime(String message){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(logo)));
        getCalendarMenu().click();
        wait.until(ExpectedConditions.visibilityOf(getAppointments()));
        getAppointments().click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(requestedTime, message))));
        return getRequestedTime(message).getText();
    }

    public void logout(){
        getAvatar().click();
        getLogout().click();
    }
}
