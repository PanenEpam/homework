package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainPage extends Page {
    @FindBy(id = "PH_user-email")
    private WebElement email;

    @FindBy(how = How.XPATH, using = "//a[@title='Написать письмо']")
    private WebElement createLetterButton;

    @FindBy(how = How.XPATH, using = "//input[@class='container--H9L5q size_s--3_M-_']")
    private WebElement addressInput;

    @FindBy(how = How.XPATH, using = "//input[@name='Subject']")
    private WebElement topicInput;

    @FindBy(how = How.XPATH, using = "//div[@role='textbox']")
    private WebElement textInput;

    @FindBy(how = How.XPATH, using = "//span[@title='Сохранить']")
    private WebElement saveButton;

    @FindBy(how = How.XPATH, using = "//button[@title='Закрыть']")
    private WebElement closeButton;

    @FindBy(how = How.XPATH, using = "//a[contains(@title,'Черновики')]")
    private WebElement draftButton;

    public MainPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driverWait.until(loadCondition);
        PageFactory.initElements(driver, this);
    }

    public String getEmail() {
        return email.getText();
    }

    public MainPage createLetter() {
        super.clickElement(createLetterButton);
        return this;
    }

    public MainPage setAddrwss(String address) {
        super.setText(addressInput, address);
        return this;
    }

    public MainPage setTopic(String topic) {
        super.setText(topicInput, topic);
        return this;
    }

    public MainPage setText(String text) {
        super.setText(textInput, text);
        return this;
    }

    public MainPage clickSaveButton() {
        super.clickElement(saveButton);
        return this;
    }

    public MainPage clickCloseBUtton() {
        super.clickElement(closeButton);
        return this;
    }

    public DraftPage clickDraftButton() {
        super.clickElement(draftButton);
        return new DraftPage(driver);
    }
}
