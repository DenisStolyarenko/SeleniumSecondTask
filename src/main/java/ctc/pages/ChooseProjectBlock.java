package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseProjectBlock extends AbstractPage{
    private static final String frameLookupDialogName = "frLookupDialog";
    private static final By CHOOSE_PROJECT_LOCATOR = By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]");
    private static final By SEARCH_INPUT_LOCATOR = By.xpath("//input[@name='keywordSearch']");
    private static final By GO_BUTTON_LOCATOR = By.xpath("//input[@value='Go']");
    private static final By OK_BUTTON_LOCATOR = By.xpath("//input[@value='OK']");

    public ChooseProjectBlock(WebDriver driver) {
        super(driver);
    }

    public ChooseProjectBlock open(){
        driver.findElement(CHOOSE_PROJECT_LOCATOR).click();
        return this;
    }

    public ChooseProjectBlock searchProjectOrCost(String projectName){
        driver.switchTo().frame(frameLookupDialogName);
        driver.findElement(SEARCH_INPUT_LOCATOR).sendKeys(projectName);
        driver.findElement(GO_BUTTON_LOCATOR).click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @projectcostobjectname='" + projectName + "']")).click();
        return this;
    }

    public ChooseProjectBlock clickByOK (){
        driver.findElement(OK_BUTTON_LOCATOR).click();
        return this;
    }

}
