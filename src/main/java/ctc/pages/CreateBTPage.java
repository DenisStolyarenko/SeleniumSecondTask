package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    Calendar calendar = Calendar.getInstance();
    Integer estimatedBudget = new Random().nextInt(100000);
    String plannedStartDate = "06/11/17"; // исправить на текущую дату inputFormat.format(date)
    String plannedEndDate = "10/11/17"; // исправить на текущую дату плюс 7 дней

    @FindBy(xpath = "//span[text()='Collapse']")
    WebElement collapseElement;

    @FindBy(xpath = "//button[text()[contains(.,'Save Changes')]]")
    WebElement saveButton;

    @FindBy(xpath = "//span[@id='plannedDuration']")
    WebElement planningDurationInput;

    @FindBy(xpath = "//a[@onclick='animateDetailsLoading()']")
    WebElement idLink;

    @FindBy(xpath = "//input[@title='Create New Business Trip Request']")
    WebElement createBtButton;

    @FindBy(xpath = "//span[@id='plannedDuration']")
    WebElement plannedEndDateInput;

    @FindBy(xpath = ".//select[@name='destinationCountryId']")
    Select countrySelect;

//    @FindBy(xpath = "//span[@id='plannedDuration']")
//    WebElement planningDurationInput;


    private static final By CHOOSE_PROJECT_LOCATOR = By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]");
    private static final String frameLookupDialogName = "frLookupDialog";
//    public static final By PLANNED_END_DATE_LOCATOR = By.xpath("//input[@id='plannedEndDate_ui']");
//    public static final By DESTINATION_COUNTRY_LOCATOR = By.xpath(".//select[@name='destinationCountryId']");


    public CreateBTPage(WebDriver driver) {
        super(driver);
    }

    public CreateBTPage newBtClick(){
        createBtButton.click();
        return this;
    }

    public CreateBTPage fillMandatoryFields(String projectName, String country, String destinationCity, String destinationAddress){

        String description = "Travel to " + destinationCity + " " + sdf.format(date);

        ChooseProjectBlock chooseProjectPage = new ChooseProjectBlock(driver).open().searchProjectOrCost(projectName).clickByOK();

        waitForElementEnabled(PLANNED_END_DATE_LOCATOR);

        Select countrySelect = new Select(driver.findElement(DESTINATION_COUNTRY_LOCATOR));
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
        By saveButton = By.xpath("//button[text()[contains(.,'Save Changes')]]");
        waitForElementEnabled(saveButton);
        driver.findElement(saveButton).click();
        String btId = driver.findElement(By.xpath("//span[@class='item' and contains(text(), 'Business Trip ID: #')]/a")).getText();
        Assert.assertEquals(btId.length(), 19, "Business Trip is not created");

//        String executeString = driver.findElement(SAVE_LOCATOR).getAttribute("onclick");
//        ((JavascriptExecutor)driver).executeScript(executeString);
//        return null;
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

    protected void waitForElementPresent(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
