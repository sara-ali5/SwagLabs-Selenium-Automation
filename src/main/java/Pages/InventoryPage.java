package Pages;
import Base.BasePages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class InventoryPage extends BasePages {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By cartIcon = By.id("shopping_cart_container");
    private final By products = By.className("inventory_item");
    private final By twitterIcon = By.cssSelector("[data-test='social-twitter']");
    private By facebookIcon =  By.cssSelector("[data-test='social-facebook']");
    private final By linkedInIcon = By.cssSelector("[data-test='social-linkedin']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By cartList = By.cssSelector("[data-test='cart-list']");



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


    @Step("Open Shopping Cart")
    public void openCart() {

        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartList)
        );
    }

    @Step("Click X (Twitter) link")
    public void clickTwitter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(twitterIcon));
        click(twitterIcon);
    }

    @Step("Click Facebook link")
    public void clickFacebook() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(facebookIcon));
        click(facebookIcon);
    }

    @Step("Click LinkedIn link")
    public void clickLinkedIn() {

        click(linkedInIcon);
    }

    @Step("Add product to cart: {productName}")
    public void addProduct(String productName) {

        By addButton = By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') " +
                        "and normalize-space()='" + productName + "']]" +
                        "//button[contains(@id,'add-to-cart')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

//        wait.until(ExpectedConditions.elementToBeClickable(addButton));
//        driver.findElement(addButton).click();

//        By removeButton = By.xpath(
//                "//div[contains(@class,'inventory_item')]" +
//                        "[.//div[contains(@class,'inventory_item_name') and normalize-space()=\"" +
//                        productName + "\"]]" +
//                        "//button[contains(@id,'remove')]"
//        );
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(removeButton));
    }


    @Step("Get price of product: {productName}")
    public double getProductPrice(String productName) {

        By productPrice = By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') " +
                        "and normalize-space()='" + productName + "']]" +
                        "//div[contains(@class,'inventory_item_price')]"
        );

        String priceText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productPrice)
        ).getText();

        return Double.parseDouble(priceText.replace("$", ""));
    }

    @Step("Get button text for product: {productName}")
    public String getProductButtonText(String productName) {

        By button = By.xpath(
                "//div[contains(@class,'inventory_item')]" +
                        "[.//div[contains(@class,'inventory_item_name') and normalize-space()=\"" +
                        productName + "\"]]" +
                        "//button"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(button)
        ).getText();
    }

    @Step("Logout")
    public void logout() {

        By menuButton = By.id("react-burger-menu-btn");
        By menu = By.cssSelector(".bm-menu-wrap");
        By logoutButton = By.id("logout_sidebar_link");
        By loginButton = By.id("login-button");

        // 1. Open the burger menu
        wait.until(
                ExpectedConditions.elementToBeClickable(menuButton)
        ).click();

        // 2. Wait until the sidebar is actually visible
        wait.until(
                ExpectedConditions.attributeToBe(
                        menu,
                        "aria-hidden",
                        "false"
                )
        );

        // 3. Wait for Logout and click it
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutButton)
        ).click();

        // 4. Verify logout was successful
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(loginButton)
        );
    }

}