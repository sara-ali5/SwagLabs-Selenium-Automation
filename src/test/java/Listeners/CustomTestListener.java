package Listeners;
import Base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class CustomTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest test = (BaseTest) result.getInstance();

        if (test.driver != null) {

            byte[] screenshot =
                    ((TakesScreenshot) test.driver)
                            .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png");
        }
    }

    @Attachment(
            value = "Screenshot",
            type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }

}
