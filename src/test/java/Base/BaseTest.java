package Base;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utilities.CustomWebDriverListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class BaseTest {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected String URL = "https://www.saucedemo.com/";
    protected WebDriverListener listener;
    protected SoftAssert softAssert;

    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutPage CheckoutPage;


    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        System.out.println("===== SET UP =====");
        System.out.println("Creating Driver for: "
                + this.getClass().getSimpleName());

        listener = new CustomWebDriverListener();

        WebDriverManager.chromedriver().setup();

        driver = new EventFiringDecorator(listener).decorate(new ChromeDriver());

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        CheckoutPage = new CheckoutPage(driver);

        // Open application
        driver.get(URL);

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        );

        softAssert = new SoftAssert();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        System.out.println("===== TEAR DOWN =====");

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}


