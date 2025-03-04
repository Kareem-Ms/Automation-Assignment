package org.example.pages;

import org.example.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenuPage {

    WebDriver driver;
    ElementActions elementActions;

    public SideMenuPage(WebDriver driver){
        this.driver=driver;
        elementActions = new ElementActions(driver);
    }

    //Actions
    public void openMenuItem(String itemName){
        String menuItemXpath = "//aside//span[text() = '"+itemName+"']";
        elementActions.click(By.xpath(menuItemXpath));
    }


}
