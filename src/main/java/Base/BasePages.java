package Base;
import io.qameta.allure.Step;
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

        WebElement element =
                wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
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

    @Step("Switch to the newly opened browser tab")
    public String switchToNewTab() {

        String parentWindow = driver.getWindowHandle();

        wait.until(driver -> {
            System.out.println("Current windows: " + driver.getWindowHandles().size());
            return driver.getWindowHandles().size() > 1;
        });

        for (String window : driver.getWindowHandles()) {

            if (!window.equals(parentWindow)) {

                driver.switchTo().window(window);
                System.out.println("Switched to: " + driver.getCurrentUrl());

                return parentWindow;
            }
        }

        throw new RuntimeException("New tab was not opened.");
    }

    @Step("Close current tab and return")
    public void closeCurrentTabAndReturn(String parentWindow) {

        driver.close();

        driver.switchTo().window(parentWindow);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='footer']")
        ));

    }


    @Step("Get current page URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}