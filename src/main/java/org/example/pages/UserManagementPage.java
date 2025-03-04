package org.example.pages;

import org.example.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserManagementPage {

    WebDriver driver;
    ElementActions elementActions;

    public UserManagementPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    //locators
    By numberOfRecordsTitleLocator = By.xpath("//span[contains(.,'Records Found')]");
    By addUserBtnLocator = By.xpath("//button[contains(.,'Add')]");
    By confirmUserDeletionBtn = By.xpath("//button[contains(.,'Delete')]");

    //Actions
    public int getNumberOfRecords(){
        String numberOfRecordsWithTxt = elementActions.getText(numberOfRecordsTitleLocator);
        return getNumberOfRecordsFromTxt(numberOfRecordsWithTxt);
    }

    private int getNumberOfRecordsFromTxt(String text){
        int startIndex = text.indexOf('(');
        int endIndex = text.indexOf(')', startIndex);

        return Integer.parseInt(text.substring(startIndex + 1, endIndex));
    }

    public void openAddUserForm(){
        elementActions.click(addUserBtnLocator);
    }

    public void deleteUserFromList(String userName){
        String trashBtnXpath = "(//div[text() = '"+userName+"']/parent::div/following-sibling::div)[4]//i[contains(@class,'bi-trash')]";
        elementActions.click(By.xpath(trashBtnXpath));
        elementActions.click(confirmUserDeletionBtn);
    }

}
