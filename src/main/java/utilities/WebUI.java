package utilities;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int PAGE_LOAD_TIMEOUT = 30;
    private static int PRESENCE_OF_ELEMENT_TIMEOUT =5;

    /**
     * Opening URL
     */
    public static void openURL(String URL){
        waitForPageLoaded();
        DriverManager.getDriver().get(URL);
    }

    /**
     * Locating WebElement
     */
    public static WebElement getWebElement(By by){
        return DriverManager.getDriver().findElement(by);
    }

    /**
     * Clicking on element
     */
    public static void clickOnWebElement(By by){
        getWebElement(by).click();
    }

    /**
     * Setting text for element
     */
    public static void setTextForElement(By by, String text){
        getWebElement(by).sendKeys(text);
    }

    /**
     * Getting text of element
     */
    public static String getTextOfElement(By by){
        waitForVisibilityOfElement(by);
        LogUtils.info("Get text of element "+ by);
        LogUtils.info("==> Text: "+ getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    /**
     * Clearing text in element
     */
    public static void clearText(By by){
        getWebElement(by).clear();
    }

    /**
     * Logging
     */
    public static void logConsole(String message){
        System.out.println(message);
    }

    /**
     * Sleeps the specified amount
     */
    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Waiting for visibility of element in UI
     */
    public static void waitForVisibilityOfElement(By by){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT),Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Verifying visibility of element in UI
     */
    public static boolean verifyVisibilityOfElement(By by){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT),Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            System.out.println("Element located by " + by + " is visible");
            return true;
        }catch (TimeoutException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Waiting for element to be present in DOM
     */
    public static void waitForPresenceOfElement(By by){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(PRESENCE_OF_ELEMENT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waiting for element to be clickable in UI
     */
    public static void waitForElementToBeClickable(By by){
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Checking WebElment(s) availability in DOM
     * @Return true if element exists in DOM, otherwise false
     */
    public static boolean checkWebElementExistence(By by){
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);

        if(listElement.size()>0){
            System.out.println("Element " + by + " - FOUND");
            return true;
        } else {
            System.out.println("Element " + by + " - NOT FOUND");
            return false;
        }
    }

    /**
     * Waiting for page to load
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            logConsole("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }
    /**
     * Waiting for JQuery to load
     */
    public static void waitForJQueryLoad() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) DriverManager.getDriver())
                    .executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            logConsole("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }


    /**
     * Highlighting border of element with red background
     */
    public static WebElement highLightElement(By by) {

        waitForVisibilityOfElement(by);
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getWebElement(by));
        }
        return getWebElement(by);
    }

    /**
     * Right-clicking on element
     */
    public static void rightClickElement(By by) {
        waitForPageLoaded();
        Actions action = new Actions(DriverManager.getDriver());
        action.contextClick(getWebElement(by));
        logConsole("Right click on element " + by);
    }

    /**
     * Hovering mouse over element
     */
    public static void hoverOnElement(By by) {
        waitForPageLoaded();
        Actions action = new Actions(DriverManager.getDriver());
        action.moveToElement(getWebElement(by));
        LogUtils.info("Hovered on element " + getWebElement(by));
    }






}
