package tests;


import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseClass;
import utils.ConfigReader;


public class LoginTest extends BaseClass {

@Test
    public void validLoginTest() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.login(ConfigReader.read("userName"), ConfigReader.read("password"));
}

}
