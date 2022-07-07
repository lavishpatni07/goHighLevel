package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.ConfirmationPage;
import pageobjects.HomePage;
import pageobjects.SchedulerInfoPage;
import pageobjects.SchedulingPage;
import pageobjects.LoginPage;
import utilities.DateTimeUtils;
import utilities.ExcelUtils;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

public class SchedulingTest extends BaseTest{
    Date scheduledDateTime;
    @DataProvider(name="Attendees")
    public Object[][] Authentication() throws Exception{
        Object[][] testObjArray = ExcelUtils.getTableArray("src//test//java//testdata//TestData.xlsx","Attendees");
        return (testObjArray);
    }

    @Test(dataProvider = "Attendees")
    public void scheduleSessionTest(String firstName, String lastName, String phoneNumber, String email) throws InterruptedException {
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(999999);
        String message = "Message "+ randomNumber;
        driver.get(env.getProperty("SCHEDULER_URL"));
        SchedulingPage schedulingPage = new SchedulingPage(driver);
        String scheduledTimeZome = schedulingPage.selectRandomTimeZone();
        String scheduledDate = schedulingPage.selectRandomDate();
        String scheduledTime = schedulingPage.selectRandomTimeSlot();
        System.out.println("Zone:"+scheduledTimeZome+" date:"+scheduledDate+" time:"+scheduledTime);
        SchedulerInfoPage schInfoPage = schedulingPage.clickContinue();
        ConfirmationPage confirmationPage = schInfoPage.scheduleFor(firstName,lastName,phoneNumber,email,message);
        Assert.assertEquals(confirmationPage.getMessage(),"Your Meeting has been Scheduled");
        DateTimeUtils dt = new DateTimeUtils();
        String date =  dt.formatDate(scheduledDate,scheduledTime);
        System.out.println(date);
        String expectedScheduleTime = dt.convertDateTo(date,scheduledTimeZome);
        driver.get(env.getProperty("HIGHLEVEL_URL"));
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.Login(env.getProperty("USER_EMAIL"),env.getProperty("USER_PASSWORD"));
        String actualSchuledTime = homePage.checkScheduledTime(message);
        Assert.assertEquals(actualSchuledTime.toLowerCase(),expectedScheduleTime.toLowerCase());
    }
}
