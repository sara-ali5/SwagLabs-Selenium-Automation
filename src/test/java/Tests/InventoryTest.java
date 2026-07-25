package Tests;
import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utilities.DataDriven;
import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Epic("Swag Labs")
@Feature("Inventory Feature")
public class InventoryTest extends BaseTest {

    LoginPage loginPage;
    InventoryPage inventoryPage;
    JsonNode data = DataDriven.jsonReader();

    @BeforeMethod(alwaysRun = true)
    public void login() {

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        String username = data.get("validUser").get("username").asText();
        String password = data.get("validUser").get("password").asText();

        loginPage.login(username, password);
    }

    @Test(groups ={"smoke", "regression"},
            description = "Verify inventory page elements")
    @Story("Inventory Page")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify page title, cart icon visibility, and product count after successful login.")
    public void verifyInventoryPageElements() {

        // Verify URL
        softAssert.assertTrue(
                inventoryPage.isInventoryPage(),
                "User is not redirected to Inventory Page");

        // Verify Page Title
        softAssert.assertEquals(
                inventoryPage.getPageTitle(),
                "Swag Labs",
                "Page title is incorrect");

        // Verify Cart Icon
        softAssert.assertTrue(
                inventoryPage.isCartDisplayed(),
                "Cart icon is not displayed");

        // Verify Products Count
        softAssert.assertEquals(
                inventoryPage.getProductsCount(),
                6,
                "Products count is incorrect");

        // Report all assertion results
        softAssert.assertAll();
    }
}

