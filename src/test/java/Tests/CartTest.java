package Tests;
import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utilities.DataDriven;
import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;


@Epic("Swag Labs")
@Feature("Cart Feature")
public class CartTest extends BaseTest {
    JsonNode data = DataDriven.jsonReader();

    @BeforeMethod(alwaysRun = true)
    public void login() {

        loginPage.login(
                data.get("validUser").get("username").asText(),
                data.get("validUser").get("password").asText()
        );

    }


    @Test(groups = {"smoke", "regression"}, description = "Verify all social media links")
    @Story("Social Links")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that LinkedIn, Facebook and X links open the correct websites.")
    public void verifySocialLinks() {


        // LinkedIn
        String parent = driver.getWindowHandle();
        inventoryPage.clickLinkedIn();
        inventoryPage.switchToNewTab();
        softAssert.assertTrue(
                inventoryPage.getCurrentUrl().contains("linkedin")
        );
        inventoryPage.closeCurrentTabAndReturn(parent);


        // Facebook
        parent = driver.getWindowHandle();
        inventoryPage.clickFacebook();
        inventoryPage.switchToNewTab();

        softAssert.assertTrue(
                inventoryPage.getCurrentUrl().contains("facebook")
        );
        inventoryPage.closeCurrentTabAndReturn(parent);


        // Twitter
        parent = driver.getWindowHandle();
        inventoryPage.clickTwitter();
        inventoryPage.switchToNewTab();
        softAssert.assertTrue(
                inventoryPage.getCurrentUrl().contains("https://x.com/saucelabs")
        );
        inventoryPage.closeCurrentTabAndReturn(parent);

        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify that the cart is empty after login")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a newly logged-in user has an empty cart before adding any products.")
    public void verifyCartIsEmpty() {

        inventoryPage.openCart();

        softAssert.assertEquals(
                cartPage.getCartItemsCount(),
                0,
                "Cart is not empty"
        );

        softAssert.assertAll();

    }

    @Step("Add all cart products from test data")
    public void addCartProducts() {

        for (JsonNode product : data.get("cartProducts")) {
            inventoryPage.addProduct(product.asText());
        }
    }


    @Test(groups = {"smoke", "regression"}, description = "Verify that 3 specific products are added to the cart in the correct order")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the three products from testData.json are added to the cart in the same order.")
    public void verifyThreeProductsAddedToCart() {

        addCartProducts();

        inventoryPage.openCart();

        List<String> actualProducts = cartPage.getCartProductNames();

        List<String> expectedProducts = new ArrayList<>();

        for (JsonNode product : data.get("cartProducts")) {
            expectedProducts.add(product.asText());
        }

        softAssert.assertEquals(
                actualProducts,
                expectedProducts,
                "Cart products are not in the expected order"
        );

        softAssert.assertEquals(
                cartPage.getCartItemsCount(),
                expectedProducts.size(),
                "Cart item count is incorrect"
        );

        softAssert.assertAll();
    }

    @Test(groups = {"smoke", "regression"}, description = "Verify that Bolt T-Shirt can be removed while other products remain in cart")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Add three products, remove Bolt T-Shirt, then verify the remaining products and inventory buttons.")
    public void verifyRemoveProductFromCart() {

        addCartProducts();

        inventoryPage.openCart();

        String productToRemove = data.get("cartProducts").get(1).asText();

        cartPage.removeProduct(productToRemove);

        List<String> actualProducts = cartPage.getCartProductNames();

        softAssert.assertEquals(
                actualProducts.size(),
                2,
                "Cart should contain two products after removal"
        );

        softAssert.assertFalse(
                actualProducts.contains(productToRemove),
                "Removed product is still present in cart"
        );

        softAssert.assertTrue(
                actualProducts.contains(data.get("cartProducts").get(0).asText()),
                "Backpack should remain in cart"
        );

        softAssert.assertTrue(
                actualProducts.contains(data.get("cartProducts").get(2).asText()),
                "Onesie should remain in cart"
        );

        // Go back to inventory
        cartPage.continueShopping();

        softAssert.assertEquals(
                inventoryPage.getProductButtonText(productToRemove),
                "Add to cart",
                "Removed product button should be 'Add to cart'"
        );

        softAssert.assertEquals(
                inventoryPage.getProductButtonText(
                        data.get("cartProducts").get(0).asText()
                ),
                "Remove",
                "Backpack button should be 'Remove'"
        );

        softAssert.assertEquals(
                inventoryPage.getProductButtonText(
                        data.get("cartProducts").get(2).asText()
                ),
                "Remove",
                "Onesie button should be 'Remove'"
        );

        softAssert.assertAll();
    }




    @Test(groups = {"smoke", "regression"}, description = "Verify cart total price matches the sum of product prices")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Read the prices of three products from the Inventory page, add them to the cart, proceed to checkout, and verify that the calculated sum matches the Item Total displayed at checkout.")
    public void verifyCartTotalPrice() {

        double expectedTotal = 0;

        // Read prices before adding products
        for (JsonNode product : data.get("cartProducts")) {

            String productName = product.asText();

            double price =
                    inventoryPage.getProductPrice(productName);

            expectedTotal += price;
        }

        // Reuse Scenario 3
        addCartProducts();

        // Open cart
        inventoryPage.openCart();

        // Proceed to checkout
        cartPage.clickCheckout();

        // Get checkout information from JSON
        JsonNode checkoutInfo = data.get("checkoutInformation");

        String firstName =
                checkoutInfo.get("firstName").asText();

        String lastName =
                checkoutInfo.get("lastName").asText();

        String postalCode =
                checkoutInfo.get("postalCode").asText();

        // Enter checkout information

        CheckoutPage.enterCheckoutInformation(
                firstName,
                lastName,
                postalCode
        );

        // Go to Checkout: Overview
        CheckoutPage.clickContinue();

        // Get Item Total
        double actualTotal =
                CheckoutPage.getItemTotal();

        System.out.println("Expected Item Total: " + expectedTotal);
        System.out.println("Actual Item Total: " + actualTotal);

        softAssert.assertEquals(
                actualTotal,
                expectedTotal,
                0.01,
                "Cart total price is incorrect"
        );

        softAssert.assertAll();
    }





    @Test(groups = {"smoke", "regression"}, description = "Verify checkout behavior when cart is empty")
    @Story("Checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Login with a valid user, ensure the cart is empty,\n" +
            "        attempt to proceed to checkout, and verify the actual\n" +
            "        application behavior and validation.")
    public void verifyCheckoutWithEmptyCart() {

        // Make sure cart is empty
        inventoryPage.openCart();

        Assert.assertEquals(
                cartPage.getCartItemsCount(),
                0,
                "Cart should be empty before starting the test"
        );

        // Attempt checkout
        cartPage.clickCheckout();

        // Verify actual navigation
        Assert.assertTrue(
                CheckoutPage.isCheckoutStepOneDisplayed(),
                "Checkout Step One should be displayed"
        );

        // Continue without entering checkout information
        CheckoutPage.clickContinuewithoutcheckoutInfo();

        // Verify required field validation
        Assert.assertEquals(
                CheckoutPage.getErrorMessage(),
                "Error: First Name is required",
                "Expected first-name validation message was not displayed"
        );
    }




    @Test(groups = {"smoke"}, description = "Verify cart state after logout and login")
    @Story("Cart Management")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify whether cart items remain after logging out and logging in again")
    public void verifyCartStateAfterLogoutLogin() {

        // Add 3 products
        addCartProducts();

        // Open cart
        inventoryPage.openCart();

        // Verify cart before logout
        Assert.assertEquals(
                cartPage.getCartItemsCount(),
                3,
                "Cart should contain 3 products before logout"
        );

        // Logout
        inventoryPage.logout();

        // Login again
        String username = data.get("validUser").get("username").asText();
        String password = data.get("validUser").get("password").asText();

        loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // Open cart again
        inventoryPage = new InventoryPage(driver);
        wait.until(
                ExpectedConditions.urlContains("inventory.html")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("shopping_cart_container")
                )
        );

        inventoryPage.openCart();

        // Check cart state
        int cartItemCount = cartPage.getCartItemsCount();

        System.out.println(
                "Cart items after re-login: " + cartItemCount
        );

        // Assert the actual behavior observed on SauceDemo
        Assert.assertEquals(
                cartItemCount,
                3,
                "Cart state after re-login is incorrect"
        );
    }

}


