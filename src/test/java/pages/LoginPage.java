package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    @FindBy(id = "username")
    public WebElement userNameField;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(id = "submit")
    public WebElement submitButton;

    @FindBy(xpath = "//strong[ contains(text(),'Congratulations')]")
    public WebElement successTextElement;

    @FindBy(xpath = "//a[contains(text(),'Log out')]")
    public WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String username) {
        userNameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        submitButton.click();
    }

    public void login(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isLogoutButtonDisplayed() {
        try {
            return logoutButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
