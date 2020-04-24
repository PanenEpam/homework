import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class ConnectionDriver {
    private static WebDriver driver;
    private static DesiredCapabilities capabilities;
    private static ChromeOptions chromeOptions;

    private static WebDriver connection(WebBrowsers browser) {
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        chromeOptions = new ChromeOptions();
        chromeOptions.merge(capabilities);

        String hubURL = "http://192.168.1.37:4444/wd/hub";
            try {
                driver = new RemoteWebDriver(new URL(hubURL), chromeOptions);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return driver;
    }

    public static WebDriver openConnection(WebBrowsers browser) {
        if (driver == null) {
            driver = connection(browser);
        }
        return driver;
    }

    public static void closeConnection() {
        if (driver != null) {
            driver.close();
            System.out.println("Driver closed");
        }
    }
}
