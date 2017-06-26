package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
    private static final int WAIT_FOR_ELEMENT_TIMEOUT_SECONDS = 20;
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

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

    protected void waitForFillingPlanningDuration(final By locator){
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_TIMEOUT_SECONDS).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                String result = d.findElement(locator).getText();
                return (!result.toLowerCase().equals("0"));
            }
        });
    }
}
