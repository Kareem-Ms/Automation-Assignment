package stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.AddUserPage;
import org.example.pages.LoginPage;
import org.example.pages.SideMenuPage;
import org.example.pages.UserManagementPage;
import org.example.utils.BrowserActions;
import org.example.utils.BrowserFactory;
import org.example.utils.JsonFileManager;
import org.example.utils.PropertiesManager;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;


public class ManageUserdefs {

    WebDriver driver;
    LoginPage loginPage;
    SideMenuPage sideMenuPage;
    UserManagementPage userManagementPage;
    AddUserPage addUserPage;
    JsonFileManager jsonFileManager;
    int initialRecordCount;

    @Given("I am in login page")
    public void iNavigateTo() {
        jsonFileManager = new JsonFileManager("src\\test\\java\\testData\\ManageUserTestData.json");
        driver = BrowserFactory.getBrowser(jsonFileManager.getTestData("Browser"));
        BrowserActions.navigateToUrl(driver, PropertiesManager.getProperty("url"));
    }

    @When("I login using a correct username and password")
    public void iloginWithUsernameAndPassword() {
        loginPage = new LoginPage(driver);
        loginPage.login(jsonFileManager.getTestData("userName"), jsonFileManager.getTestData("password"));
    }

    @When("I click on Admin tab on the left side menu")
    public void iClickOnAdminTab() {
        sideMenuPage = new SideMenuPage(driver);
        sideMenuPage.openMenuItem(jsonFileManager.getTestData("menuItemName"));
    }

    @And("I get the number of records found")
    public void iGetTheNumberOfRecordsFound() {
        userManagementPage = new UserManagementPage(driver);
        initialRecordCount = userManagementPage.getNumberOfRecords();
    }

    @And("I add user by filling the required data")
    public void iFillTheRequiredData() {
        addUserPage = new AddUserPage(driver);
        userManagementPage.openAddUserForm();
        addUserPage.addUser(jsonFileManager.getTestData("employeeInfo.userRole")
                            , jsonFileManager.getTestData("employeeInfo.status")
                            , jsonFileManager.getTestData("employeeInfo.name")
                            , jsonFileManager.getTestData("employeeInfo.userName")
                            , jsonFileManager.getTestData("employeeInfo.password"));
    }

    @Then("I verify that the number of records increased by 1")
    public void iVerifyTheNumberOfRecordsIncreasedBy1() {
        Assert.assertEquals(initialRecordCount+1,userManagementPage.getNumberOfRecords());
        initialRecordCount = userManagementPage.getNumberOfRecords();
    }


    @When("I delete the new user")
    public void iDeleteTheNewUser() {
        userManagementPage.deleteUserFromList(jsonFileManager.getTestData("employeeInfo.userName"));
    }

    @Then("I verify that the number of records decreased by 1")
    public void iVerifyTheNumberOfRecordsDecreasedBy1() {
        Assert.assertEquals(initialRecordCount-1,userManagementPage.getNumberOfRecords());
        BrowserActions.closeAllBrowserTabs(driver);
    }
}
