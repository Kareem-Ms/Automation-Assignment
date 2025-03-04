package org.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ElementActions {
    private final WebDriver driver;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public  void click(By elementLocator) {
        mouseHover(elementLocator);

        try {
            getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            System.out.println(toe.getMessage());
        }

        try {
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {
            // Click on element using JavascriptExecutor in case of the click is not performed
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                        driver.findElement(elementLocator));
            } catch (Exception e) {
                System.out.println("Couldn't click on the element:" + elementLocator);
            }
        }
    }

    public  void mouseHover(By elementLocator) {
        locatingElement(elementLocator);
        try {
            new Actions(driver)
                    .moveToElement(driver.findElement(elementLocator))
                    .perform();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void type(By elementLocator, String text) {
        locatingElement(elementLocator);
        try {
            driver.findElement(elementLocator).clear();

            driver.findElement(elementLocator).sendKeys(text);

            if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
                // If it wasn't written successfully we try to type using JavascriptExecutor
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].setAttribute('value', '" + text + "')",
                        driver.findElement(elementLocator));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public String getText(By elementLocator) {
        locatingElement(elementLocator);
        try {
            return driver.findElement(elementLocator).getText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void locatingElement(By elementLocator) {
        try {
            getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            if (!driver.findElement(elementLocator).isDisplayed()) {
                System.out.println("The element [\" + elementLocator.toString() + \"] is not Displayed");
            }
        } catch (TimeoutException toe) {
            System.out.println(toe.getMessage());
        }
    }

}