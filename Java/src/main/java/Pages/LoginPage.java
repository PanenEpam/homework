package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Page {
    @FindBy(id = "mailbox:login")
    private WebElement loginInput;

    @FindBy(id = "mailbox:password")
    private WebElement passwordInput;

    @CacheLookup
    @FindBy(id = "mailbox:submit")
    private WebElement bntSubmit;

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driverWait.until(loadCondition);
        PageFactory.initElements(driver, this);
    }

    public LoginPage setLogin(String login) {
        super.setText(loginInput, login);
        return this;
    }

    public LoginPage setPassword(String psw) {
        super.setText(passwordInput, psw);
        return this;
    }

    public void clickSubmitButton() {
        super.clickElement(bntSubmit);
    }


}
