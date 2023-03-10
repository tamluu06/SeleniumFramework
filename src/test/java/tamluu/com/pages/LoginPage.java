package tamluu.com.pages;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import utilities.WebUI;
import static utilities.WebUI.*;

public class LoginPage {

    private String URL = "https://rise.fairsketch.com/signin";
    private String PAGE_TEXT = "Login";

    //Declaring locators of objects in Login page

    By emailInput = By.xpath("//input[@id='email']");
    By passwordInput = By.xpath("//input[@id='password']");
    By loginButton = By.xpath("//button[normalize-space()='Sign in']");
    By emailErrorMessage = By.xpath("//form[@id='signin-form']//div[1]");
    By pageTitle = By.xpath("//h4[normalize-space()='Dashboard']");

    public void enterEmail(String email){
        WebUI.setTextForElement(emailInput,email);
    }
    public void enterPassword(String password){
        WebUI.setTextForElement(passwordInput,password);
    }
      public void clickOnLoginButton(){
        WebUI.clickOnWebElement(loginButton);
    }
    public void verifyEmailErrorMessage(){
        Assert.assertTrue(DriverManager.getDriver().findElement(emailErrorMessage).isDisplayed(),"No error message displayed");
        Assert.assertEquals(WebUI.getTextOfElement(emailErrorMessage),"Authentication failed!","Error message NOT match");

    }

    public void loginValid(String email, String password){
        openURL(URL);
        WebUI.clearText(emailInput);
        enterEmail(email);
        WebUI.clearText(passwordInput);
        enterPassword(password);
        clickOnLoginButton();
        WebUI.getTextOfElement(pageTitle);
        WebUI.verifyVisibilityOfElement(pageTitle);
    }
    public void loginInvalid(String email, String password){
        WebUI.openURL(URL);
        WebUI.clearText(emailInput);
        enterEmail(email);
        WebUI.clearText(passwordInput);
        enterPassword(password);
        clickOnLoginButton();
        WebUI.waitForVisibilityOfElement(emailErrorMessage);
        verifyEmailErrorMessage();

    }

}
