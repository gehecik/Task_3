package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.example.utils.EnvConfig.BASE_URL;
import static org.example.utils.EnvConfig.EXPLICIT_TIMEOUT;

public class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkClickableForLocator(By locator) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitClickable(By locator) {
        return new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement checkLocator(By locator) {
        return new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitLocator(By locator) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitPage(String endpoint, By locator) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.or(
                        ExpectedConditions.urlToBe(BASE_URL),
                        ExpectedConditions.urlContains(endpoint),
                        ExpectedConditions.presenceOfElementLocated(locator)
                ));
    }

    public String getAccessToken(By locator) throws InterruptedException {
        waitLocator(locator);
        Thread.sleep(5000);
        return (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('accessToken');");
    }

    public void enterNewValue(By locator, String newValue) {
//        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
//
//        waitLocator(locator);
//        checkClickableForLocator(locator);
//
//        WebElement webElement = driver.findElement(locator);
//
//        webElement.click();
//        webElement.clear();
//        webElement.sendKeys(newValue);
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);

        WebElement element = wait.until(driver ->
                driver.findElement(locator)
        );
        waitLocator(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.clear();
        element.sendKeys(newValue);
    }
}
