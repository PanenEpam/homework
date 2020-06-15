package Tests;

import Data.DataProviders;
import Letters.Letter;
import Pages.DraftPage;
import Pages.LoginPage;
import Pages.MainPage;
import Users.User;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class LetterTest extends MainTest {
    private static final Logger LOG = Logger.getLogger(LetterTest.class);
    private LoginPage loginPage;
    private MainPage mainPage;
    private DraftPage draftPage;
    private Letter letter;

    public LetterTest(String address, String topic, String text){
        letter = new Letter(address, topic, text);
    }

    @BeforeClass(description = "open browser")
    public void openBrowser() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @Test(description = "login to mail service", dataProvider = "loginData", dataProviderClass = DataProviders.class)
    public void doLogin(String login, String password) {
        User user = new User(login, password);

        loginPage.setLogin(user.getLogin());
        loginPage.clickSubmitButton();
        loginPage.setPassword(user.getPassword());
        loginPage.clickSubmitButton();

        Assert.assertEquals(mainPage.getEmail(), user.getLogin() + "@mail.ru");
    }

    @Test(description = "letter creation",dependsOnMethods = {"doLogin"})
    public void createLetter() {

        mainPage.createLetter();
        mainPage.setAddress(letter.getAddress());
        mainPage.setTopic(letter.getTopic());
        mainPage.setText(letter.getText());
        mainPage.clickSaveButton();
        mainPage.clickCloseBUtton();
    }

    @Test(description = "check Topic and Text", dependsOnMethods = {"createLetter"})
    public void checkInfo() {
        mainPage.clickDraftButton();
        draftPage = new DraftPage(driver);

        Assert.assertEquals(letter.getTopic(), draftPage.getTopicLetter());
        Assert.assertEquals(letter.getText(), draftPage.getTextLetter());
    }

    @Test(description = "drop letter", dependsOnMethods = {"checkInfo"})
    public void dropLetter() {
        LOG.info("Delete letter");
        draftPage.dropLetter();
        Assert.assertTrue(draftPage.isExist(), "Letter deleted");
    }

    @AfterClass(description = "logout")
    public void logout() {
        driver.findElement(By.id("PH_logoutLink")).click();
    }
}
