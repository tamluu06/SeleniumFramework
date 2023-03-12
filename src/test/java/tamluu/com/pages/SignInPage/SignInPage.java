package tamluu.com.pages.SignInPage;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.testng.Assert;
import tamluu.com.pages.DashboardPage.DashboardPage;
import utilities.WebUI;

import static utilities.WebUI.openURL;

public class SignInPage {

    private String URL = "https://rise.fairsketch.com/signin";
    private String PAGE_TEXT = "Login";

    //Declaring locators of objects in Login page

    public By emailInput = By.xpath("//input[@id='email']");
    public By passwordInput = By.xpath("//input[@id='password']");
    public By loginButton = By.xpath("//button[normalize-space()='Sign in']");
    public By emailErrorMessage = By.xpath("//form[@id='signin-form']//div[1]");
    public By pageTitle = By.xpath("//h4[normalize-space()='Dashboard']");
    public By projectsOverview = By.xpath("//div[@id='page-content']/div[4]/div[1]/div[1]/div[1]");

    public By invoiceOverview = By.xpath("//div[@id='page-content']/div[4]/div[2]/div[1]/div[1]/div[1]");


    public void enterEmail(String email) {
        WebUI.setTextForElement(emailInput, email);
    }

    public void enterPassword(String password) {
        WebUI.setTextForElement(passwordInput, password);
    }

    public void clickOnLoginButton() {
        WebUI.clickOnWebElement(loginButton);
    }

    public void verifyEmailErrorMessage() {
        Assert.assertTrue(DriverManager.getDriver().findElement(emailErrorMessage).isDisplayed(), "No error message displayed");
        Assert.assertEquals(WebUI.getTextOfElement(emailErrorMessage), "Authentication failed!", "Error message NOT match");
    }

    public DashboardPage signInAsAdmin(String email, String password) {
        openURL(URL);
        WebUI.clearText(emailInput);
        enterEmail(email);
        WebUI.clearText(passwordInput);
        enterPassword(password);
        clickOnLoginButton();
        WebUI.getTextOfElement(pageTitle);
        return new DashboardPage();
    }

    public DashboardPage signIn(String email, String password) {
        openURL(URL);
        WebUI.clearText(emailInput);
        enterEmail(email);
        WebUI.clearText(passwordInput);
        enterPassword(password);
        clickOnLoginButton();
        WebUI.getTextOfElement(pageTitle);
        return new DashboardPage();
    }

    public void signInInvalid(String email, String password) {
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
