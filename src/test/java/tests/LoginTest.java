package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseClass;
import utils.ConfigManager;


public class LoginTest extends BaseClass {


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
        Assert.assertTrue(successText.contains("Congratulations"),
                "Success message does not contains expected text. Actual message: " + successText);

        // 4. Verify button Log out is displayed on the new page
        waitForVisibilityOfElement(loginPage.logoutButton);
        Assert.assertTrue(loginPage.isLogoutButtonDisplayed(),
                "Logout button is not displayed on the page after login.");

    }

}
