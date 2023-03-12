package tamluu.com.testcases;

import dataproviders.SignInData;
import org.testng.annotations.Test;
import tamluu.com.base.BaseTest;
import tamluu.com.pages.SignInPage.SignInPage;

import java.util.Hashtable;

public class SignInTest extends BaseTest {

    SignInPage signInPage;

    @Test
    public void signInPositive(){
        signInPage = new SignInPage();
        signInPage.signInAsAdmin("admin@demo.com", "riseDemo");
    }

    @Test(dataProvider ="data_provider_signin_test_excel", dataProviderClass = SignInData.class)
    public void signInWithDataFromExcel(String email, String password, String result){
        signInPage = new SignInPage();
        signInPage.signIn(email, password);
    }

    @Test(dataProvider ="data_provider_signin_test_excel_custom_row", dataProviderClass = SignInData.class)
    public void signInWithDataFromExcelCustomRow(Hashtable<String, String>data){
        signInPage = new SignInPage();
        signInPage.signIn(data.get("EMAIL"), data.get("PASSWORD"));
    }

    @Test
    public void signInNegative() {
        signInPage = new SignInPage();
        signInPage.signInInvalid("admin@demo1.com", "riseDemo");
    }
}
