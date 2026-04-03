package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseClass {

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
            driver = null;
        }
    }

public static WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT));
}

public static void waitForVisibilityOfElement (WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
}
public static void waitForElementToBeCLickable (WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
}

public static String takeScreenshot (String testName) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath= "test-output/screenshots/" + testName + "_" + timeStamp + ".png";

        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);

        try {
            // Without this, screenshot saving can fail if /screenshots/ does not exist yet.
            File parentDir = destFile.getParentFile();
            if (parentDir != null && !parentDir.exists()){
                parentDir.mkdirs();
            }
            FileUtils.copyFile(srcFile,destFile);
            logger.info("Screenshot saved at: {}", screenshotPath);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: "+ e.getMessage());
        }
        return screenshotPath;
}
}

