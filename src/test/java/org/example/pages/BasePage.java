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


    public WebElement waitPresence(By locator) {
        return new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
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

    public void goToElement(WebElement element) {
        new org.openqa.selenium.interactions.Actions(driver)
                .moveToElement(element)
                .pause(java.time.Duration.ofMillis(300))
                .perform();
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
    }

    public void waitOverlayDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".Modal_modal_overlay__x2ZCr")
            ));
        } catch (Exception ignored) {
        }
    }
}
