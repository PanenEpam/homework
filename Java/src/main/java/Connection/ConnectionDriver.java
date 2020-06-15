package Connection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ConnectionDriver {
    private static WebDriver driver;

    private static WebDriver connection(String browser) {
        switch (browser) {
            case "CHROME":
                driver = new ChromeDriver();
            case "FIREFOX":
                driver = new FirefoxDriver();
            default:
                break;
        }
        return driver;
    }

    public static WebDriver openConnection(String browser) {
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
