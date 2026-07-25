package Pages;

import Base.BasePages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePages {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By cartIcon = By.id("shopping_cart_container");
    private final By products = By.className("inventory_item");

    @Step("Verify Inventory Page URL")
    public boolean isInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    @Step("Verify Inventory Page Title")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Verify that the Cart icon is displayed")
    public boolean isCartDisplayed() {
        return isDisplayed(cartIcon);
    }

    @Step("Get inventory products count")
    public int getProductsCount() {
        return driver.findElements(products).size();
    }
}