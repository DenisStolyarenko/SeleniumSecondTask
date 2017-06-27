package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BTListPage extends AbstractPage {
    private static final String BUSINESS_TRIP_LOCATION = "/businesstrip/list.do";
    private static final By BT_LIST_NAME_LOCATOR = By.xpath("//td[@class='header1']/h1");
    private static final By CREATE_BUTTON_LOCATOR = By.xpath("//input[@title='Create New Business Trip Request']");

    public BTListPage(WebDriver driver) {
        super(driver);
    }

    public BTListPage open(String baseUrl){
        getDriver().get(baseUrl + BUSINESS_TRIP_LOCATION);
        return this;
    }

    public CreateBTPage newBtClick(){
        waitForElementEnabled(CREATE_BUTTON_LOCATOR);
        getDriver().findElement(CREATE_BUTTON_LOCATOR).click();
        return new CreateBTPage(getDriver());
    }

    public String readListName(){
        String result = getDriver().findElement(BT_LIST_NAME_LOCATOR).getText();
        return result;
    }
}
