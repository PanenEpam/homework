import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConnectionDriver {
    private static WebDriver driver;

    private static WebDriver createCinnection( WebBrowsers browser) {
        switch (browser){
            case CHROME:
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;

            default:
                break;
        }
        return driver;
    }

    public static WebDriver openConnection(WebBrowsers browser) {
        if (driver == null) {
            driver = createCinnection(browser);
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
