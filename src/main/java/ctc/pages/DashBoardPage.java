package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashBoardPage extends AbstractPage {
    private static final By LOGGED_LABEL_LOCATOR = By.xpath("//td[@id='headerLogin']/div[@class='blInfoLogin']");
    private static final By DASHBOARD_LABEL_LOCATOR = By.xpath("//td[@class='header1']/h1[text()='Dashboard']");

    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    public String readLoggedinText(){
        waitForElementPresent(DASHBOARD_LABEL_LOCATOR);
        String result = getDriver().findElement(LOGGED_LABEL_LOCATOR).getText();
        return result;
    }
}
