package Tests;

import Connection.Config;
import Connection.ConnectionDriver;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MainTest {
    private static final Logger LOG = Logger.getLogger(MainTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setUp() {
        LOG.info("Creation connecting");
        DOMConfigurator.configure("src/main/resources/log4j.xml");
        driver = Config.getDriver();
        Config.setDriverProperty(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

    @AfterSuite(alwaysRun = true, description = "close browser")
    public void closeBrowser() {
        ConnectionDriver.closeConnection();
    }
}
