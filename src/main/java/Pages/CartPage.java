package Pages;
import Base.BasePages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePages {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By cartItems = By.cssSelector(".cart_item");

    private final By cartItemNames =
            By.cssSelector(".cart_item .inventory_item_name");

    private final By cartItemPrices =
            By.cssSelector("[data-test='inventory-item-price']");

    private final By checkoutButton =
            By.cssSelector("[data-test='checkout']");

    private final By continueShoppingBtn = By.cssSelector("[data-test='continue-shopping']");


    @Step("Get cart items count")
    public int getCartItemsCount() {

        return driver.findElements(cartItems).size();
    }


    @Step("Click Checkout button")
    public void clickCheckout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }



    @Step("Verify checkout button displayed")
    public boolean isCheckoutDisplayed() {

        return driver.findElements(checkoutButton).size() > 0;
    }


    @Step("Get product names from cart")
    public List<String> getCartProductNames() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartItems)
        );

        List<WebElement> elements =
                driver.findElements(cartItemNames);

        List<String> productNames = new ArrayList<>();

        for (WebElement element : elements) {
            productNames.add(element.getText().trim());
        }

        return productNames;
    }



    private By removeButton(String productName) {
        String productId = productName
                .toLowerCase()
                .replace("sauce labs ", "")
                .replace(" ", "-");

        return By.cssSelector("button[data-test='remove-sauce-labs-" + productId + "']");
    }

    @Step("Remove product from cart: {productName}")
    public void removeProduct(String productName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                removeButton(productName)
        )).click();
    }


    @Step("Continue shopping")
    public void continueShopping() {

        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Page title: " + driver.getTitle());

        wait.until(
                ExpectedConditions.elementToBeClickable(continueShoppingBtn)
        ).click();
    }
}
