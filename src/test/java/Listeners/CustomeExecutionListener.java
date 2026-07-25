package Listeners;
import Utilities.CMDRunner;
import org.testng.IExecutionListener;

public class CustomeExecutionListener implements IExecutionListener {
    private final String deleteJsonCommand= "cmd /c cd target/allure-results && del /g *.json";
    private final String generateAllureReportCommand= "cmd /c allure generate target/allure-results -o reports/ --clean --single-file";

    @Override
    public void onExecutionStart() {
        CMDRunner.executeCMD(deleteJsonCommand);

        System.out.println("ExecutionStart");
    }


    @Override
    public void onExecutionFinish() {
        CMDRunner.executeCMD(generateAllureReportCommand);

        System.out.println("ExecutionFinish");
    }
}
