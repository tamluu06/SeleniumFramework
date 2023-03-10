package tamluu.com.testcases;

import org.testng.annotations.Test;
import tamluu.com.base.BaseTest;
import tamluu.com.pages.SignInPage.SignInPage;

public class SignInTest extends BaseTest {

    SignInPage signInPage;

    @Test
    public void signInPositive(){
        signInPage = new SignInPage();
        signInPage.signInAsAdmin("admin@demo.com", "riseDemo");
    }


    @Test
    public void signInNegative() {
        signInPage = new SignInPage();
        signInPage.signInInvalid("admin@demo1.com", "riseDemo");
    }
}
