package utilities;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
}
