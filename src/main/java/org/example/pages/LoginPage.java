package org.example.pages;

import org.example.utils.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;
    ElementActions elementActions;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    //locators
    By userNameInputLocator = By.name("username");
    By passwordInputLocator = By.name("password");
    By loginBtnLocator = By.xpath("//button[@type = 'submit']");

    //Actions
    public void login(String userName, String password){
        elementActions.type(userNameInputLocator,userName);
        elementActions.type(passwordInputLocator,password);
        elementActions.click(loginBtnLocator);
    }


}
