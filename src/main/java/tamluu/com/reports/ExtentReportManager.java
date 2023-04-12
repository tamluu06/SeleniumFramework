package tamluu.com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("tamluu/com/reports/extentreport/extentreport.html");
        reporter.config().setReportName("Extent Report | TamLuu CRM");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | TestNG");
        extentReports.setSystemInfo("Author", "Tam Luu");
        return extentReports;
    }

}