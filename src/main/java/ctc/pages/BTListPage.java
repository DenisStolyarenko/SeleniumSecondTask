package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BTListPage extends AbstractPage {
    private static final String BUSINESS_TRIP_LOCATION = "/businesstrip/list.do";
    private static final By BT_LIST_NAME_LOCATOR = By.xpath("//td[@class='header1']/h1");

    public BTListPage(WebDriver driver) {
        super(driver);
    }

    public BTListPage open(String baseUrl){
        driver.get(baseUrl + BUSINESS_TRIP_LOCATION);
        return this;
    }

    public String readListName(){
        String result = driver.findElement(BT_LIST_NAME_LOCATOR).getText();
        return result;
    }
}
