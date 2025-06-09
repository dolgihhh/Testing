package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverManager;


public class BaseTest {

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        WebDriver driver = new ChromeDriver(options);
        DriverManager.setDriver(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();
    }
}
