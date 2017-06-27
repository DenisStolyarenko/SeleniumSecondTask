package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreateBTPage extends AbstractPage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
    private static final Date currentDate = new Date();
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final DateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
    private static long msDay = 7 * 24 * 60 * 60 * 1000;  //миллисекунд в 7 сутках
    private static final Integer estimatedBudget = new Random().nextInt(100000);

    private static final String plannedStartDate = inputFormat.format(currentDate); //
    private static final String plannedEndDate = inputFormat.format(currentDate.getTime() + msDay);

    private static final By COLLAPSE_LOCATOR = By.xpath("//span[text()='Collapse']");
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//button[text()[contains(.,'Save Changes')]]");
    private static final By PLANNED_DURATION_LOCATOR = By.xpath("//span[@id='plannedDuration']");
    private static final By ID_LINK_LOCATOR = By.xpath("//a[@onclick='animateDetailsLoading()']");
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
    public static final By BT_ID_LOCATOR = By.xpath("//span[@class='item' and contains(text(), 'Business Trip ID: #')]/a");

    public CreateBTPage(WebDriver driver) {
        super(driver);
    }

    public CreateBTPage fillMandatoryFields(String projectName, String country, String destinationCity, String destinationAddress) {
        String description = "Travel to " + destinationCity + " " + sdf.format(currentDate);
        ChooseProjectBlock chooseProjectPage = new ChooseProjectBlock(getDriver()).open().searchProjectOrCost(projectName).clickByOK();
        waitForElementEnabled(PLANNED_END_DATE_LOCATOR);
        Select countrySelect = new Select(getDriver().findElement(DESTINATION_COUNTRY_LOCATOR));
        countrySelect.selectByVisibleText(country);
        getDriver().findElement(DESTINATION_CITY_LOCATOR).sendKeys(destinationCity);
        getDriver().findElement(DESTINATION_ADDRESS_LOCATOR).sendKeys(destinationAddress);
        getDriver().findElement(DESCRIPTION_LOCATOR).sendKeys(description);
        if (!getDriver().findElement(TICKET_LOCATOR).isSelected()) {
            getDriver().findElement(TICKET_LOCATOR).click();
        }
        if (!getDriver().findElement(CAR_LOCATOR).isSelected()) {
            getDriver().findElement(CAR_LOCATOR).click();
        }
        getDriver().findElement(ESTIMATE_BUDGET_LOCATOR).sendKeys(estimatedBudget.toString());
        getDriver().findElement(PLANNED_START_DATE_LOCATOR).sendKeys(plannedStartDate);
        getDriver().findElement(PLANNED_END_DATE_LOCATOR).sendKeys(plannedEndDate);
        getDriver().findElement(SUMMARY_LOCATOR).sendKeys("BT created by Selenium " + sdf.format(currentDate));
        return this;
    }

    public CreateBTPage saveForm() {
        waitForFillingPlanningDuration(PLANNED_DURATION_LOCATOR);
//        waitForElementEnabled(SAVE_BUTTON_LOCATOR);
//        driver.findElement(SAVE_BUTTON_LOCATOR).click();

        String executeString = getDriver().findElement(SAVE_BUTTON_LOCATOR).getAttribute("onclick");
        ((JavascriptExecutor)getDriver()).executeScript(executeString);
        return this;
    }

    public String getBTid(){
        String result = getDriver().findElement(BT_ID_LOCATOR).getText();
        return result;
    }

    protected void waitForFillingPlanningDuration(final By locator) {
        new WebDriverWait(getDriver(), WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                String result = d.findElement(locator).getText();
                return (!result.toLowerCase().equals("0"));
            }
        });
    }

}
