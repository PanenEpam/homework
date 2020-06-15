package Connection;

import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Config {
    private static String browser;
    private static String driverType;
    private static String driverName;
    private static String baseURL;
    private static String path = "src/main/resources/DriverData.xml";

    public static void setPath(String path) {
        Config.path = path;
    }

    public static WebDriver getDriver() {
        getDriverData();
        return ConnectionDriver.openConnection(browser);
    }

    public static void setDriverProperty(WebDriver driver) {
        System.setProperty(driverType, driverName);
        driver.get(baseURL);
    }

    private static void getDriverData() {
        try {
            File file = new File(path);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList nList = document.getElementsByTagName("driver");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    browser = element.getElementsByTagName("browser").item(0).getTextContent();
                    driverType = element.getElementsByTagName("driverType").item(0).getTextContent();
                    driverName = element.getElementsByTagName("driverName").item(0).getTextContent();
                    baseURL = element.getElementsByTagName("baseURL").item(0).getTextContent();
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
