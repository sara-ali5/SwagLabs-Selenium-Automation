package Base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePages {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePages(WebDriver driver) {

        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void click(By locator) {

        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

    }

    public void type(By locator, String text) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        driver.findElement(locator).clear();

        driver.findElement(locator).sendKeys(text);

    }

    public String getText(By locator) {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();

    }

    public boolean isDisplayed(By locator) {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();

    }

}