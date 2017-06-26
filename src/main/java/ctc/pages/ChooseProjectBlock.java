package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChooseProjectBlock extends AbstractPage{
    private static final String frameLookupDialogName = "frLookupDialog";

    @FindBy(xpath = "//img[contains(@onclick,'chooseprojectcostobject')]")
    WebElement chooseProject;

    @FindBy(xpath = "//input[@name='keywordSearch']")
    WebElement searchInput;

    @FindBy(xpath = "//input[@value='Go']")
    WebElement goButton;

    @FindBy(xpath = "//input[@value='OK']")
    WebElement okButton;


    public ChooseProjectBlock(WebDriver driver) {
        super(driver);
    }

    public ChooseProjectBlock open(){
        chooseProject.click();
        return this;
    }

    public ChooseProjectBlock searchProjectOrCost(String projectName){
        driver.switchTo().frame(frameLookupDialogName);
        searchInput.sendKeys(projectName);
        goButton.click();
        driver.findElement(By.xpath("//input[@type='checkbox' and @projectcostobjectname='" + projectName + "']"))
                .click();
        return this;
    }

    public ChooseProjectBlock clickByOK (){
        okButton.click();
        return this;
    }

}
