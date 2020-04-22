package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DraftPage extends Page{
    @FindBy(how = How.XPATH, using = "//span[@class='ll-sj__normal']")
    private WebElement topicLetter;

    @FindBy(how = How.XPATH, using = "//span[@class='ll-sp__normal']")
    private WebElement textLetter;

    //@FindBy(how = How.XPATH, using = "//a[contains(@class,'llct')]")
    @FindBy(className = "llct__container")
    private WebElement letter;

    @FindBy(how = How.XPATH, using = "//a[@title='Корзина']")
    private WebElement trashBin;

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
        new Actions(driver).dragAndDrop(letter, trashBin);
        return this;
    }
}
