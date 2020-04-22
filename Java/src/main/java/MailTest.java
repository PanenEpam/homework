import Pages.DraftPage;
import Pages.LoginPage;
import Pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class MailTest {
    private WebDriver driver;
    private String login;
    private String password;
    private String addressee;
    private String topic;
    private String text;
    private LoginPage loginPage;
    private MainPage mainPage;
    private DraftPage draftPage;


    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeClass(description = "open browser")
    public void openBrowser() {
        driver = ConnectionDriver.openConnection(WebBrowsers.CHROME);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
        driver.get("http://mail.ru/");
    }


    @Test(description = "login to mail service", dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void doLogin(String login, String password) {
        this.login = login;
        this.password = password;

        loginPage = new LoginPage(driver);
        loginPage.setLogin(login);
        loginPage.clickSubmitButton();
        loginPage.setPassword(password);
        loginPage.clickSubmitButton();

        mainPage = new MainPage(driver);
        Assert.assertEquals(mainPage.getEmail(), login + "@mail.ru");
    }

    @Test(description = "letter creation", dataProvider = "createData", dataProviderClass = DataProviders.class,
            dependsOnMethods = {"doLogin"})
    public void createLetter(String addressee, String topic, String text) {
        this.addressee = addressee;
        this.topic = topic;
        this.text = text;

        mainPage.createLetter();
        mainPage.setAddrwss(addressee);
        mainPage.setTopic(topic);
        mainPage.setText(text);
        mainPage.clickSaveButton();
        mainPage.clickCloseBUtton();
    }

    @Test(description = "check Topic and Text", dependsOnMethods = {"createLetter"})
    public void checkInfo() {
        mainPage.clickDraftButton();
        draftPage = new DraftPage(driver);

        Assert.assertEquals(topic, draftPage.getTopicLetter());
        Assert.assertEquals(text, draftPage.getTextLetter());
    }

    @Test(description = "logout", dependsOnMethods = {"dropLetter"})
    public void logout() {
        driver.findElement(By.id("PH_logoutLink")).click();
    }

    @AfterClass(alwaysRun = true, description = "close browser")
    public void closeBrowser() {
        ConnectionDriver.closeConnection();
    }
}
