package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateBTPage extends AbstractPage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
    private static final Date currentDate = new Date();
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final DateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
    private static long msDay = 7 * 24 * 60 * 60 * 1000;  //сколько миллисекунд в 7 сутках
    private static final Integer estimatedBudget = new Random().nextInt(100000);

    private static final String plannedStartDate = inputFormat.format(currentDate); //
    private static final String plannedEndDate = inputFormat.format(currentDate.getTime() + msDay);
    ; // исправить на текущую дату плюс 7 дней

    private static final By COLLAPSE_LOCATOR = By.xpath("//span[text()='Collapse']");
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//button[text()[contains(.,'Save Changes')]]");
    private static final By PLANNED_DURATION_LOCATOR = By.xpath("//span[@id='plannedDuration']");
    private static final By ID_LINK_LOCATOR = By.xpath("//a[@onclick='animateDetailsLoading()']");
    private static final By CREATE_BUTTON_LOCATOR = By.xpath("//input[@title='Create New Business Trip Request']");
    private static final By PLANNED_START_DATE_LOCATOR = By.xpath("//input[@id='plannedStartDate_ui']");
    private static final By PLANNED_END_DATE_LOCATOR = By.xpath("//input[@id='plannedEndDate_ui']");
    private static final By DESTINATION_COUNTRY_LOCATOR = By.xpath(".//select[@name='destinationCountryId']");
    private static final By DESTINATION_CITY_LOCATOR = By.xpath("//input[@name='destinationCity']");
    private static final By DESTINATION_ADDRESS_LOCATOR = By.xpath("//textarea[@name='destinationAddress']");
    private static final By DESCRIPTION_LOCATOR = By.xpath("//textarea[@id='description']");
    private static final By TICKET_LOCATOR = By.xpath("//input[@id='ticketsRequired']");
    private static final By CAR_LOCATOR = By.xpath("//input[@id='carRequired']");
    private static final By ESTIMATE_BUDGET_LOCATOR = By.xpath("//input[@class='textfield textfieldDigit textfieldAmount' and @name='estimatedBudget']");
    private static final By SUMMARY_LOCATOR = By.xpath("//input[@name='itemName']");


    private static final By CHOOSE_PROJECT_LOCATOR = By.xpath("//img[contains(@onclick,'chooseprojectcostobject')]");

    private static final String frameLookupDialogName = "frLookupDialog";


    public CreateBTPage(WebDriver driver) {
        super(driver);
    }

    public CreateBTPage newBtClick() {
        driver.findElement(CREATE_BUTTON_LOCATOR).click();
        return this;
    }

    public CreateBTPage fillMandatoryFields(String projectName, String country, String destinationCity, String destinationAddress) {
        String description = "Travel to " + destinationCity + " " + sdf.format(currentDate);
        ChooseProjectBlock chooseProjectPage = new ChooseProjectBlock(driver).open().searchProjectOrCost(projectName).clickByOK();
        waitForElementEnabled(PLANNED_END_DATE_LOCATOR);
        Select countrySelect = new Select(driver.findElement(DESTINATION_COUNTRY_LOCATOR));
        countrySelect.selectByVisibleText(country);
        driver.findElement(DESTINATION_CITY_LOCATOR).sendKeys(destinationCity);
        driver.findElement(DESTINATION_ADDRESS_LOCATOR).sendKeys(destinationAddress);
        driver.findElement(DESCRIPTION_LOCATOR).sendKeys(description);
        if (!driver.findElement(TICKET_LOCATOR).isSelected()) {
            driver.findElement(TICKET_LOCATOR).click();
        }
        if (!driver.findElement(CAR_LOCATOR).isSelected()) {
            driver.findElement(CAR_LOCATOR).click();
        }
        driver.findElement(ESTIMATE_BUDGET_LOCATOR).sendKeys(estimatedBudget.toString());
        driver.findElement(PLANNED_START_DATE_LOCATOR).sendKeys(plannedStartDate);
        driver.findElement(PLANNED_END_DATE_LOCATOR).sendKeys(plannedEndDate);
        driver.findElement(SUMMARY_LOCATOR).sendKeys("BT created by Selenium " + sdf.format(currentDate));
        return this;
    }

    public CreateBTPage saveForm() {
        waitForFillingPlanningDuration(PLANNED_DURATION_LOCATOR);
//        waitForElementEnabled(SAVE_BUTTON_LOCATOR);
//        driver.findElement(SAVE_BUTTON_LOCATOR).click();

        String executeString = driver.findElement(SAVE_BUTTON_LOCATOR).getAttribute("onclick");
        ((JavascriptExecutor)driver).executeScript(executeString);
        return this;
    }

    @Override
    protected void waitForFillingPlanningDuration(final By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                String result = d.findElement(locator).getText();
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
