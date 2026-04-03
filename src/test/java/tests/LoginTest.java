package tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.BaseClass;
import utils.ConfigManager;
import utils.ExtentReportManager;

import java.lang.reflect.Method;


public class LoginTest extends BaseClass {

    private static ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void initializeReport(){
        extent = ExtentReportManager.getExtentReports();
    }

    @BeforeMethod(alwaysRun = true)
    public void createTestEntry (Method method) {
        test = extent.createTest(method.getName());
    }

    @Test(
            description = "Verify that user can login with valid credentials",
            groups = {"smoke", "regression"}
    )
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigManager.getUsername(), ConfigManager.getPassword());

        // 1. Verify new page URL contains practicetestautomation.com/logged-in-successfully/
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("practicetestautomation.com/logged-in-successfully/"),
                "URL do not contain expected text. Current URL: " + currentUrl);

        // 2. Verify new page contains expected text ('Congratulations')
        waitForVisibilityOfElement(loginPage.successTextElement);
        String successText = loginPage.successTextElement.getText();
        Assert.assertTrue(successText.contains("Congratulations") || successText.contains("successfully logged in"),
                "Success message does not contains expected text. Actual message: " + successText);

        // 4. Verify button Log out is displayed on the new page
        waitForVisibilityOfElement(loginPage.logoutButton);
        Assert.assertTrue(loginPage.isLogoutButtonDisplayed(),
                "Logout button is not displayed on the page after login.");
    }

    @AfterMethod(alwaysRun = true)
    public void captureResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test passed successfully");
        } else if (result.getStatus() == ITestResult.FAILURE){
            test.fail(result.getThrowable());

            String screenshotPath = takeScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test was skipped");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
