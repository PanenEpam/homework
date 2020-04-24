package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DraftPage extends Page{
    private static final By NOTIFY_MESSAGE = By.xpath("//div[@class='notify__content']");

    @FindBy(how = How.XPATH, using = "//span[@class='ll-sj__normal']")
    private WebElement topicLetter;

    @FindBy(how = How.XPATH, using = "//span[@class='ll-sp__normal']")
    private WebElement textLetter;

    @FindBy(how = How.XPATH, using = "//a[contains(@class,'llct')]")
    private WebElement letter;

    @FindBy(how = How.XPATH, using = "//a[contains(@title,'Корзина')]")
    private WebElement basket;

    @FindBy(how = How.XPATH, using = "//button[contains(@class,'ll-av ')]")
    private WebElement avatar;

    public DraftPage(WebDriver driver){
        super(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driverWait.until(loadCondition);
        PageFactory.initElements(driver, this);
    }

    public String getTopicLetter(){
        return topicLetter.getText();
    }

    public String getTextLetter(){
        return textLetter.getText();
    }

    public DraftPage dropLetter(){
        new Actions(driver).moveToElement(avatar).pause(Duration.ofMillis(100)).click().build().perform();
        new Actions(driver).dragAndDrop(letter, basket).pause(Duration.ofMillis(3000)).build().perform();
        return this;
    }

    public boolean isExist(){
        return driverWait.until(ExpectedConditions.visibilityOfElementLocated(NOTIFY_MESSAGE)).isDisplayed();
    }

}
