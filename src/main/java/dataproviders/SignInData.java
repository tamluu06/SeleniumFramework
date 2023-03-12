package dataproviders;

import helpers.ExcelHelper;
import helpers.SystemsHelper;
import org.testng.annotations.DataProvider;

public class SignInData {
    @DataProvider(name = "dataProviderSignInCRM", parallel = true)
    public Object[][] dataSignInCRM() {
        return new Object[][]{{"admin@demo.com", "riseDemo"}};
    }

    @DataProvider(name = "data_provider_signin_test_excel")
    public Object[][] dataSignInCRMFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData(SystemsHelper.getCurrentDir() + "src/main/resources/testdata/CRM.xlsx", "Signin");
        return data;
    }

    @DataProvider(name = "data_provider_signin_test_excel_custom_row")
    public Object[][] dataSignInCRMFromExcelWithCustomRow() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemsHelper.getCurrentDir() + "src/main/resources/testdata/CRM.xlsx", "SigninCustom", 3, 4);
        return data;
    }


}
