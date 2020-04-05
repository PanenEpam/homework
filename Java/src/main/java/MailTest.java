import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class MailTest {
    WebDriver driver;
    String addressee;
    String topic;
    String text;


    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @BeforeClass(description = "open browser")
    public void openBrowser() {
        driver = ConnectionDriver.openConnection(WebBrowsers.CHROME);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://mail.ru/");
    }


    @Test(description = "login to mail service")
    public void doLogin() {

        driver.findElement(By.id("mailbox:login")).sendKeys("epam_homework");
        driver.findElement(By.id("mailbox:submit")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("mailbox:password")).sendKeys("helloepam123");
        driver.findElement(By.id("mailbox:submit")).click();
    }

    @Test(description = "letter creation", dataProvider = "createData", dataProviderClass = DataProviders.class,
            dependsOnMethods = {"doLogin"})
    public void createLetter(String addressee, String topic, String text) {

        this.addressee = addressee;
        this.topic = topic;
        this.text = text;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@title='Написать письмо']")).click();
        driver.findElement(By.xpath("//input[@class='container--H9L5q size_s--3_M-_']")).sendKeys(addressee);
        driver.findElement(By.xpath("//input[@name='Subject']")).sendKeys(topic);
        driver.findElement(By.xpath("//div[@role='textbox']")).clear();
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(text);
        driver.findElement(By.xpath("//span[@title='Сохранить']")).click();
        driver.findElement(By.xpath("//button[@title='Закрыть']")).click();
        driver.findElement(By.xpath("//a[@title='Черновики']")).click();

    }

    @Test(description = "check Topic and Text", dependsOnMethods = {"createLetter"})
    public void checkInfo() {

        String topicLetter = driver.findElement(By.xpath("//span[@class='ll-sj__normal']")).getText();
        String textLetter = driver.findElement(By.xpath("//span[@class='ll-sp__normal']")).getText();
        Assert.assertEquals(topic, topicLetter);
        Assert.assertEquals(text, textLetter);

    }

    @Test(description = "logout", dependsOnMethods = {"checkInfo"})
    public void logout() {
        driver.findElement(By.id("PH_logoutLink")).click();
    }

    @AfterClass(alwaysRun = true, description = "close browser")
    public void closeBrowser() {
        ConnectionDriver.closeConnection();
    }
}
