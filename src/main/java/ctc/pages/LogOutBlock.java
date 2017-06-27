package ctc.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutBlock extends AbstractPage {
    private static final By LOGOUT_LOCATOR = By.xpath("//a[@href='logout.do']");
    private static final By LOGIN_PAGE_TEXT_LOCATOR = By.xpath("//td[@class='header1']/h1");
    private static final String textWelcome = "Welcome to EPAM Cost Tracking Center";

    public LogOutBlock(WebDriver driver) {
        super(driver);
    }

    public LogOutBlock logout(){
        waitForElementVisible(LOGOUT_LOCATOR);
        waitForElementEnabled(LOGOUT_LOCATOR);
        driver.findElement(LOGOUT_LOCATOR).click();
        return this;
    }

    public LogOutBlock confirmation(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }


}
