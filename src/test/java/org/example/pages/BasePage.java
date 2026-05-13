package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

//        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
//
//        WebElement element = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(locator)
//        );
//
//        return wait.until(
//                ExpectedConditions.elementToBeClickable(element)
//        );
    }

    public WebElement checkLocator(By locator) {
        return new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitLocator(By locator) {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public String getAccessToken(By locator) throws InterruptedException {
        waitLocator(locator);

        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return window.localStorage.getItem('accessToken');")
                                != null
                );

        return (String) ((JavascriptExecutor) driver).executeScript(
                "return window.localStorage.getItem('accessToken');");
    }

    public void setAccessToken(String accessToken) throws InterruptedException {
        driver.get(BASE_URL);

        ((JavascriptExecutor) driver).executeScript(
                "window.localStorage.setItem('accessToken', arguments[0]);",
                accessToken
        );

        driver.navigate().refresh();
    }

    public void enterNewValue(By locator, String newValue) {
        WebElement element = new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        element.clear();
        element.sendKeys(newValue);

//        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//
//        driver.findElement(locator).clear();
//
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//
//        driver.findElement(locator).sendKeys(newValue);
    }
}
