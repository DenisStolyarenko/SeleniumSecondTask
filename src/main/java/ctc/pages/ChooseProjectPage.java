package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChooseProjectPage extends AbstractPage{
    private static final By CHOOSE_PROJECT_LOCATOR = By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]");
    private static final String frameLookupDialogName = "frLookupDialog";

    public ChooseProjectPage(WebDriver driver) {
        super(driver);
    }

    public ChooseProjectPage open(){
        driver.findElement(CHOOSE_PROJECT_LOCATOR)
                .click();
        return this;
    }

    public ChooseProjectPage searchProjectOrCost(String projectName){
        driver.switchTo().frame(frameLookupDialogName);
        driver.findElement(By.xpath("//input[@name='keywordSearch']"))
                .sendKeys(projectName);
        driver.findElement(By.xpath("//input[@value='Go']"))
                .click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @projectcostobjectname='" + projectName + "']"))
                .click();
        return this;
    }

    public ChooseProjectPage applyFounded (){
        driver.findElement(By.xpath("//input[@value='OK']"))
                .click();
        return this;
    }

}
