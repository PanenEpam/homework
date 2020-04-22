package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected ExpectedCondition<Boolean> loadCondition;
    protected Actions actions;

    protected Page(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, 15);
        this.loadCondition = webDriver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        this.actions = new Actions(driver);
    }

    public void clickElement(WebElement element) {
        actions.click(element).pause(Duration.ofMillis(1000)).build().perform();
    }

    public void setText(WebElement element, String text) {
        element.clear();
        actions.sendKeys(element, text).pause(Duration.ofMillis(1000)).build().perform();
    }
}
