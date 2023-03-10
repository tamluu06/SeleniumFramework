package utilities;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebUI {

    public static void openURL(String URL){
        DriverManager.getDriver().get(URL);
    }
    public static WebElement getWebElement(By by){
        return DriverManager.getDriver().findElement(by);
    }
    public static void clickOnWebElement(By by){
        getWebElement(by).click();
    }
    public static void setTextForElement(By by, String text){
        getWebElement(by).sendKeys(text);
    }
    public static String getTextOfElement(By by){
        return getWebElement(by).getText();
    }
    public static void clearText(By by){
        getWebElement(by).clear();
    }
    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForVisibilityOfElement(By by){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5),Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static boolean verifyVisibilityOfElement(By by){
        try{
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(5),Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }catch (TimeoutException e){
            e.printStackTrace();
            return false;
        }
    }





}
