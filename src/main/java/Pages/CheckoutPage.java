package Pages;
import Base.BasePages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePages {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private final By FirstName =
            By.cssSelector("[data-test='firstName']");

    private final By LastName =
            By.cssSelector("[data-test='lastName']");

    private final By PostalCode =
            By.cssSelector("[data-test='postalCode']");

    private final By cancelButton =
            By.cssSelector("[data-test='cancel']");

    private final By continueButton =
            By.cssSelector("[data-test='continue']");

    private final By pageTitle =
            By.cssSelector("[data-test='title']");

    private final By itemTotal =
            By.cssSelector(".summary_subtotal_label");

    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    private final By checkoutStepOne =
            By.cssSelector("[data-test='checkout-info-container']");

    @Step("Enter checkout information")
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(FirstName))
                .sendKeys(firstName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(LastName))
                .sendKeys(lastName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(PostalCode))
                .sendKeys(postalCode);
    }

    @Step("Continue to checkout overview")
    public void clickContinue() {

        System.out.println("First Name value: " +
                driver.findElement(FirstName).getAttribute("value"));

        System.out.println("Last Name value: " +
                driver.findElement(LastName).getAttribute("value"));

        System.out.println("Postal Code value: " +
                driver.findElement(PostalCode).getAttribute("value"));

        System.out.println("URL before Continue: " +
                driver.getCurrentUrl());

        wait.until(ExpectedConditions.elementToBeClickable(continueButton))
                .click();

        System.out.println("URL after Continue: " +
                driver.getCurrentUrl());
    }



    @Step("Get item total")
    public double getItemTotal() {

        String totalText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(itemTotal)
        ).getText();

        return Double.parseDouble(
                totalText.replace("Item total: $", "").trim()
        );
    }

    @Step("Verify Checkout Step One is displayed")
    public boolean isCheckoutStepOneDisplayed() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        checkoutStepOne
                )
        ).isDisplayed();
    }

    @Step("Get checkout error message")
    public String getErrorMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();
    }

    @Step("Continue without checkout information")
    public void clickContinuewithoutcheckoutInfo() {

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }


}
