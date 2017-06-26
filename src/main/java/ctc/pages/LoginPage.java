package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    private final String ADDITIONAL_URL = "/login.do?logout=true&tz=GMT%2B06:00";
    private static final By USER_NAME_INPUT_LOCATOR = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//input[@name='Login']");
    private static final By LOGGED_LABEL_LOCATOR = By.xpath("//td[@id='headerLogin']/div[@class='blInfoLogin']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(String userName, String pwdName){
        driver.findElement(USER_NAME_INPUT_LOCATOR).sendKeys(userName);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(userName);
        driver.findElement(LOGIN_BUTTON_LOCATOR).click();
        return this;
    }

    public LoginPage open(String baseUrl){
        driver.get(baseUrl + ADDITIONAL_URL);
        return this;
    }

    public String readLoggedinText(){
        String result = driver.findElement(LOGGED_LABEL_LOCATOR).getText();
        return result;
    }
}
