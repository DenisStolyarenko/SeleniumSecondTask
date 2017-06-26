package ctc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BTListPage extends AbstractPage {
    private static final String BUSINESS_TRIP_LOCATION = "/businesstrip/list.do";

    @FindBy(xpath = "//td[@class='header1']/h1")
    WebElement btListName;

    public BTListPage(WebDriver driver) {
        super(driver);
    }

    public BTListPage open(String baseUrl){
        driver.get(baseUrl + BUSINESS_TRIP_LOCATION);
        return this;
    }

    public String readListName(){
        String result = btListName.getText();
        return result;
    }
}
