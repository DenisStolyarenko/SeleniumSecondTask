package ctc;

import ctc.Service.WebDriverFactory;
import ctc.pages.BTListPage;
import ctc.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CTCTest {
    private WebDriver driver;

    private static final String baseUrl = "https://tst1.epm-ctc.projects.epam.com/";
    private static final String userName = "dst";
    private static final String pwdName = "0";
    private static final String projectName = "ENRC-TRD";
    private static final String country = "Belarus";
    private static final String destinationCity = "Minsk";
    private static final String destinationAddress = "Minsk";
    private static final String firstName = "Denis";
    private static final String textAfterSuccessfulLogin = "Logged in as " + firstName;
    String sectionName = "Business Trips";


    @Test(description = "Log in")
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver).open(baseUrl).login(userName, pwdName);
        Assert.assertTrue(loginPage.readLoggedinText().contains(textAfterSuccessfulLogin), "Impossible to login to CTC");
    }

    @Test(dependsOnMethods = "loginTest", description = "check opening the list of Bussiness Trips")
//    @Parameters({"baseUrl"})
    public void openListOfBT() {
        BTListPage btListPage = new BTListPage(driver).open(baseUrl);
        Assert.assertEquals(btListPage.readListName(), sectionName, "The section did not found");
    }

    @Test(dependsOnMethods = "openListOfBT", description = "create new BT")
    @Parameters({"projectName", "country", "destinationCity", "destinationAddress"})
    public void createNewBt(){

    }

    @BeforeClass(description = "Start browser")
    public void initBrowser() {
        WebDriverFactory.startBrowser();

    }

    @AfterClass(description = "Close browser")
    public void closeBrowser() throws InterruptedException {
        WebDriverFactory.closeBrowser();

    }
}
