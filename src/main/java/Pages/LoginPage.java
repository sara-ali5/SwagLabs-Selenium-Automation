package Pages;
import Base.BasePages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePages {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    @Step("Enter username: {0}")
    public void enterUsername(String user) {
        type(username, user);
    }

    @Step("Enter password")
    public void enterPassword(String pass) {
        type(password, pass);
    }

    @Step("Click Login button")
    public void clickLogin() {
        click(loginBtn);
    }

    @Step("Login using username: {0}")
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }
}