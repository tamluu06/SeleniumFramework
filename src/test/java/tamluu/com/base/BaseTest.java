package tamluu.com.base;

import tamluu.com.drivers.DriverManager;
import tamluu.com.helpers.PropertiesHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import tamluu.com.listeners.TestListener;


@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browserName"})
    public static void createDriver(@Optional("chrome") String browserName) {
        WebDriver driver = setupDriver(browserName);
        PropertiesHelper.loadAllFiles();
        //Set initiated driver to ThreadLocal
        DriverManager.setDriver(driver);
    }

    public static WebDriver setupDriver(String browserName) {
        WebDriver driver;
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, launching Chrome as default browser...");
                driver = initChromeDriver();
        }
        return driver;
    }


    private static WebDriver initChromeDriver() {
        WebDriver driver;
        System.out.println("Launching Chrome browser...");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        WebDriver driver;
        System.out.println("Launching Edge browser...");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        WebDriver driver;
        System.out.println("Launching Firefox browser...");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }



    @AfterMethod(alwaysRun = true)
    public static void tearDown() {
        //Eliminating driver
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

}
