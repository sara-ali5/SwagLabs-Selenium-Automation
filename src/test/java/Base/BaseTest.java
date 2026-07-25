package Base;
import Utilities.CustomWebDriverListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected String URL = "https://www.saucedemo.com/";
    protected WebDriverListener listener;
    protected SoftAssert softAssert;



    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("===== BEFORE CLASS =====");

        listener = new CustomWebDriverListener();
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringDecorator(listener)
                .decorate(new ChromeDriver());

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        System.out.println("===== BEFORE METHOD =====");
        driver.get(URL);
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void afterClass() {

        if (driver != null)
            driver.quit();

    }

}
