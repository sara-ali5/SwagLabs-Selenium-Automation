package Tests;
import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import Utilities.DataDriven;
import Utilities.ScreenshotUtils;
import com.fasterxml.jackson.databind.JsonNode;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Swag Labs")
@Feature("Login Feature")
public class LoginTest extends BaseTest {
    LoginPage loginPage;
    InventoryPage inventoryPage;
    JsonNode data = DataDriven.jsonReader();




    @Test(groups = {"smoke", "regression"}, description = "Verify successful login with valid credentials")
    @Story("Successful Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that the user can login successfully using valid credentials loaded from JSON.")
    public void verifySuccessfulLogin() {

        System.out.println("Driver in test = " + driver);

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        System.out.println(inventoryPage);

        String username = data.get("validUser").get("username").asText();
        String password = data.get("validUser").get("password").asText();

        loginPage.login(username, password);
        ScreenshotUtils.attachScreenshot("Inventory Page", driver);

        Assert.assertTrue(inventoryPage.isInventoryPage(),
                "User is not redirected to Inventory Page");

    }



    @Test(groups = {"regression"})
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that an appropriate error message is displayed when invalid credentials are used.")
    public void verifyInvalidLogin() {

        loginPage = new LoginPage(driver);

        String username = data.get("invalidUser").get("username").asText();
        String password = data.get("invalidUser").get("password").asText();

        loginPage.login(username, password);

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Username and password do not match"),
                "Wrong error message");

    }




    @Test(groups = {"regression"})
    @Story("Login Without Password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the system displays an error message when the password field is empty.")
    public void verifyLoginWithoutPassword() {

        loginPage = new LoginPage(driver);

        String username = data.get("emptyPassword").get("username").asText();
        String password = data.get("emptyPassword").get("password").asText();

        loginPage.login(username, password);

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Password is required"),
                "Wrong error message");

    }

}
