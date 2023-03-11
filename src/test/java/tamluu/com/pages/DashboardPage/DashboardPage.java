package tamluu.com.pages.DashboardPage;

import org.openqa.selenium.By;
import utilities.WebUI;

public class DashboardPage {
    private String URL ="https://rise.fairsketch.com/dashboard";
    private String PAGE_TEXT="Dashboard";

    public By pageTitle = By.xpath("//h4[normalize-space()='Dashboard']");
    public By projectsOverview = By.xpath("//div[@id='page-content']/div[4]/div[1]/div[1]/div[1]");

    public By invoiceOverview = By.xpath("//div[@id='page-content']/div[4]/div[2]/div[1]/div[1]/div[1]");

    public void validateElementsOnDashboard() {
        WebUI.verifyVisibilityOfElement(pageTitle);
        WebUI.verifyVisibilityOfElement(projectsOverview);
        WebUI.getTextOfElement(projectsOverview);
        WebUI.getTextOfElement(invoiceOverview);
    }

}
