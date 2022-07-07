package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pageobjects.HomePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    Properties env;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        env = new Properties();
        try (InputStream input = new FileInputStream("src/test/java/testdata/env.properties")) {
            env.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @AfterMethod
    public void openScheduler() {
        new HomePage(driver).logout();
        driver.quit();
    }
}
