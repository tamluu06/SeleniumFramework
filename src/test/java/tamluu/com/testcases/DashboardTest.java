package tamluu.com.testcases;

import org.testng.annotations.Test;
import tamluu.com.base.BaseTest;
import tamluu.com.pages.DashboardPage.DashboardPage;
import tamluu.com.pages.SignInPage.SignInPage;

public class DashboardTest extends BaseTest {

    SignInPage signInPage;
    DashboardPage dashboardPage;

    @Test
    public void validateElementsOnDashboardPage() {
        signInPage = new SignInPage();
        dashboardPage = signInPage.signInAsAdmin("admin@demo.com", "riseDemo");
        dashboardPage.validateElementsOnDashboard();
    }

}
