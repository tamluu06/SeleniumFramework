package tamluu.com.testcases;

import org.testng.annotations.Test;
import tamluu.com.base.BaseTest;
import tamluu.com.pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test

    public void loginPositive(){
        loginPage = new LoginPage();
        loginPage.loginValid("admin@demo.com","riseDemo");
    }
    @Test

    public void loginNegative(){
        loginPage = new LoginPage();
        loginPage.loginInvalid("admin@demo1.com","riseDemo");
    }
}
