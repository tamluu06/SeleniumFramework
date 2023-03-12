package tamluu.com.base;

import drivers.DriverManager;
import helpers.CaptureHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeMethod
    @Parameters({"browserName"})
    public static void createDriver(@Optional("chrome") String browserName) {
        WebDriver driver = setupDriver(browserName);
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
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
    public static void tearDown(ITestResult iTestResult) {
        //Taking screenshots of failed testcases
        if(iTestResult.getStatus() == ITestResult.FAILURE){
            CaptureHelper.captureScreenshot(iTestResult.getName());
        }
        //Eliminating driver
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

}
