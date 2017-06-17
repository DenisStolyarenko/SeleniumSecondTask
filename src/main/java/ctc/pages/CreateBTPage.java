package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class CreateBTPage extends AbstractPage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    Integer estimatedBudget = new Random().nextInt(100000);
    String plannedStartDate = "06/11/17"; // исправить на текущую дату inputFormat.format(date)
    String plannedEndDate = "10/11/17"; // исправить на текущую дату плюс 7 дней

    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final By COLLAPSE_LOCATOR = By.xpath("//span[text()='Collapse']");
    private static final By SAVE_LOCATOR = By.xpath("//*[@id='saveButton']/button");
    private static final By PLANNING_DURATION_LOCATOR = By.xpath("//span[@id='plannedDuration']");
    private static final By ID_LINK_LOCATOR = By.xpath("//a[@onclick='animateDetailsLoading()']");
    private static final By NEW_BUTTON_LOCATOR = By.xpath("//input[@title='Create New Business Trip Request']");
    private static final By CHOOSE_PROJECT_LOCATOR = By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]");
    private static final String frameLookupDialogName = "frLookupDialog";




    public CreateBTPage(WebDriver driver) {
        super(driver);
    }

    public CreateBTPage newBT(){
        driver.findElement(NEW_BUTTON_LOCATOR)
                .click();
        return this;
    }

    public CreateBTPage fillMandatoryFields(String projectName, String country, String destinationCity, String destinationAddress){
        String description = "Travel to " + destinationCity + " " + sdf.format(date);

        ChooseProjectPage chooseProjectPage = new ChooseProjectPage(driver).open().searchProjectOrCost(projectName).applyFounded();

//        driver.switchTo().frame(frameLookupDialogName);
//        driver.findElement(By.xpath("//input[@name='keywordSearch']"))
//                .sendKeys(projectName);
//        driver.findElement(By.xpath("//input[@value='Go']"))
//                .click();
//        driver.findElement(By.xpath("//input[@type='checkbox' and @projectcostobjectname='" + projectName + "']"))
//                .click();
//        driver.findElement(By.xpath("//input[@value='OK']"))
//                .click();
        waitForElementEnabled(By.xpath("//input[@id='plannedEndDate_ui']"));
        Select countrySelect = new Select(driver.findElement(By.xpath(".//select[@name='destinationCountryId']")));
        countrySelect
                .selectByVisibleText(country);
        driver.findElement(By.xpath("//input[@name='destinationCity']"))
                .sendKeys(destinationCity);
        driver.findElement(By.xpath("//textarea[@name='destinationAddress']"))
                .sendKeys(destinationAddress);
        driver.findElement(By.xpath("//textarea[@id='description']"))
                .sendKeys(description);
        if ( !driver.findElement(By.xpath("//*[@id='ticketsRequired']")).isSelected() ){
            driver.findElement(By.xpath("//*[@id='ticketsRequired']")).click();
        }
        if ( !driver.findElement(By.xpath("//*[@id='carRequired']")).isSelected() ){
            driver.findElement(By.xpath("//*[@id='carRequired']")).click();
        }
        driver.findElement(By.xpath("//input[@class='textfield textfieldDigit textfieldAmount' and @name='estimatedBudget']"))
                .sendKeys(estimatedBudget.toString());
        driver.findElement(By.xpath("//input[@id='plannedStartDate_ui']"))
                .sendKeys(plannedStartDate);
        driver.findElement(By.xpath("//input[@id='plannedEndDate_ui']"))
                .sendKeys(plannedEndDate);
        driver.findElement(By.xpath("//input[@name='itemName']"))
                .sendKeys("BT created by Selenium " + sdf.format(date));
        return null;
    }

    public CreateBTPage saveForm(){
        String executeString = driver.findElement(SAVE_LOCATOR).getAttribute("onclick");
        ((JavascriptExecutor)driver).executeScript(executeString);
        return null;
    }

    @Override
    protected void waitForFillingPlanningDuration(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                String result = d.findElement(PLANNING_DURATION_LOCATOR).getText();
                return (!result.toLowerCase().equals("0"));
            }
        });
    }
}
