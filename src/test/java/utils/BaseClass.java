package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseClass {

    private static final Logger log = LoggerFactory.getLogger(BaseClass.class);
    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseClass.class);

    @BeforeMethod(alwaysRun = true)
    public void openBrowserAndLaunchApplication () {
        BrowserType browser = ConfigManager.getBrowser();
        logger.info("Launching browser: {}", browser);

        switch (browser){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: "+browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        driver.get(ConfigManager.getApplicationUrl());

        logger.info("Application launched successfully: {}", ConfigManager.getApplicationUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown () {
        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
        }
    }
public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
}

public static void waitForVisibilityOfElement (WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
}
public static void waitForElementToBeCLickable (WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
}
}

