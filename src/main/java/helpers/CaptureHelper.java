package helpers;

import drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelper {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void captureScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            //capture screenshot - getScreenshotAs
            File source = ts.getScreenshotAs(OutputType.FILE);
            // If no folder exists, create one
            File theDir = new File(SystemsHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH"));
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            FileHandler.copy(source, new File(SystemsHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + screenshotName + "_" + dateFormat.format(new Date()) + ".png"));
            System.out.println("Screenshot taken: " + screenshotName);
            System.out.println("Screenshot taken current URL: " + DriverManager.getDriver().getCurrentUrl());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
