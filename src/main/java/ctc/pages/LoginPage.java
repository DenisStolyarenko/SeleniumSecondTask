package ctc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    private final String ADDITIONAL_URL = "/login.do?logout=true&tz=GMT%2B06:00";
    private static final By USER_NAME_INPUT_LOCATOR = By.xpath("//input[@name='username']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//input[@name='Login']");
    private static final By LOGIN_PAGE_TEXT_LOCATOR = By.xpath("//td[@class='header1']/h1");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashBoardPage login(String userName, String pwdName){
        getDriver().findElement(USER_NAME_INPUT_LOCATOR).sendKeys(userName);
        getDriver().findElement(PASSWORD_INPUT_LOCATOR).sendKeys(pwdName);
        getDriver().findElement(LOGIN_BUTTON_LOCATOR).click();
        return new DashBoardPage(getDriver());
    }

    public LoginPage open(String baseUrl){
        getDriver().get(baseUrl + ADDITIONAL_URL);
        return this;
    }

    public String readIntroductionText(){
        String result = getDriver().findElement(LOGIN_PAGE_TEXT_LOCATOR).getText();
        return result;
    }
}
