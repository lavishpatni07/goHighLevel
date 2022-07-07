package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class SchedulingPage {
    WebDriver driver;
    private String evenetName = "h4.widgets--service-name";
    private String selectableDates = "td.vdpCell.selectable";
    private String timeSlots = "li.widgets-time-slot";
    private String timezoneSelector = "div.multiselect__select";
    private String availableTimeZones = "li.multiselect__element span.option__title";
    private String continueButton = "button.btn.btn-schedule";
    private Random random =  new Random();
    public SchedulingPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getEvenetName() {
        return driver.findElement(By.cssSelector(evenetName));
    }

    public List<WebElement> getSelectableDates() {
        return driver.findElements(By.cssSelector(selectableDates));
    }

    public List<WebElement> getTimeSlots() {
        return driver.findElements(By.cssSelector(timeSlots));
    }

    public WebElement getTimezoneSelector() {
        return driver.findElement(By.cssSelector(timezoneSelector));
    }

    public List<WebElement> getAvailableTimeZones() {
        return driver.findElements(By.cssSelector(availableTimeZones));
    }

    public WebElement getContinueButton() {
        return driver.findElement(By.cssSelector(continueButton));
    }

    public String selectRandomDate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(this.selectableDates)));
        List<WebElement> dates =  getSelectableDates();
        int randomDatePosition = random.nextInt(dates.size());
        WebElement randomDate = dates.get(randomDatePosition);
        randomDate.click();
        return randomDate.getText();
    }

    public String selectRandomTimeSlot(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(this.timeSlots)));
        List<WebElement> timeSlots = getTimeSlots();
        int randomTimeSlotPosition = random.nextInt(timeSlots.size());
        WebElement timeSlot = timeSlots.get(randomTimeSlotPosition);
        timeSlot.click();
        return timeSlot.getText();
    }

    public String selectRandomTimeZone() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(this.timezoneSelector)));
        getTimezoneSelector().click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(this.availableTimeZones)));
        List<WebElement> timeZones = getAvailableTimeZones();
        int randomTimeZonePositions = random.nextInt(timeZones.size());
        WebElement timeZone = timeZones.get(randomTimeZonePositions);
        Actions action = new Actions(driver);
        action.moveToElement(timeZone).click().build().perform();
        return timeZone.getText();
    }

    public SchedulerInfoPage clickContinue(){
        getContinueButton().click();
        return new SchedulerInfoPage(driver);
    }
}
