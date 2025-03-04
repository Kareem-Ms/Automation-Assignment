package org.example.pages;

import org.example.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AddUserPage {

    WebDriver driver;
    ElementActions elementActions;

    public AddUserPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    //Locators
    By saveBtnInputLocator = By.xpath("//button[@type = 'submit']");


    //Actions
    public void addUser(String userRole, String status, String employeeName,String userName, String password){
        selectUserRole(userRole);
        selectUserStatus(status);
        selectEmployee(employeeName);
        elementActions.type(getInputLocatorByTitle("Username"),userName);
        elementActions.type(getInputLocatorByTitle("Password"),password);
        elementActions.type(getInputLocatorByTitle("Confirm Password"), password);
        elementActions.click(saveBtnInputLocator);
    }

    public void selectUserRole(String userRole){
        opneSelectByTitle("User Role");
        String userRoleXpath = "//div[@role = 'option']//span[text()='"+userRole+"']";
        elementActions.click(By.xpath(userRoleXpath));
    }

    public void selectUserStatus(String status){
        opneSelectByTitle("Status");
        String statusXpath = "//div[@role = 'option']//span[text()='"+status+"']";
        elementActions.click(By.xpath(statusXpath));
    }

    public void selectEmployee(String employeeName){
        elementActions.type(getInputLocatorByTitle("Employee Name"), employeeName);
        String EmployeeNameXpath = "//div[@role = 'option']//span[text()='"+employeeName+"']";
        elementActions.click(By.xpath(EmployeeNameXpath));
    }

    public void opneSelectByTitle(String title){
        String selectLocatorXpath = "//label[contains(.,'"+title+"')]/parent::div/following-sibling::div";
        elementActions.click(By.xpath(selectLocatorXpath));
    }

    private By getInputLocatorByTitle(String title){
        String titleXpath = "//label[contains(.,'"+title+"')]/parent::div/following-sibling::div//input";
        return By.xpath(titleXpath);
    }
}
