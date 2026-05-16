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
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);


        wait.until( driver -> {
            try {
                WebElement element = driver.findElement(locator);
                element.clear();
                element.sendKeys(newValue);
                return element.getAttribute("value").equals(newValue);
            } catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public void waitOverlayDisappear() {
        By overlay = By.cssSelector(".Modal_modal_overlay__x2ZCr");
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);

        wait.until(driver -> {
            try {
                return driver.findElements(overlay).stream().noneMatch(WebElement::isDisplayed);
            } catch (StaleElementReferenceException e) {
                return true;
            }
        });
    }

    public void waitURL() {
        new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.urlContains("/login"));
    }
}
