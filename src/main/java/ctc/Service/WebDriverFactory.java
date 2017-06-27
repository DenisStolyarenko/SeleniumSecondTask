package ctc.Service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
    protected static WebDriver driver;

    public WebDriverFactory(WebDriver driver) {
        this.driver = driver;
//        PageFactory.initElements(driver, this);
    }

    public WebDriverFactory() {

    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void startBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(WAIT_FOR_ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void closeBrowser(){
        driver.quit();
    }


}
