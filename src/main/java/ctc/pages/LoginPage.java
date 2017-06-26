package ctc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {
    private final String ADDITIONAL_URL = "/login.do?logout=true&tz=GMT%2B06:00";

    @FindBy(xpath = "//input[@name='username']")
    WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//input[@name='Login']")
    WebElement loginButton;

    @FindBy(xpath = "//td[@id='headerLogin']/div[@class='blInfoLogin']")
    WebElement loggedLabel;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(String userName, String pwdName){
        usernameInput.sendKeys(userName);
        passwordInput.sendKeys(pwdName);
        loginButton.click();
        return this;
    }

    public LoginPage open(String baseUrl){
        driver.get(baseUrl + ADDITIONAL_URL);
        return this;
    }

    public String readLoggedinText(){
        String result = loggedLabel.getText();
        return result;
    }
}
