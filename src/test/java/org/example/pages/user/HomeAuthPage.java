package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.utils.EnvConfig.EXPLICIT_TIMEOUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeAuthPage extends BasePage {

    protected final By locatorAccessToken = By.xpath("//p[text()='Личный Кабинет']");
    protected final By userStatusContent = By.className("button_button_size_large__G21Vg");
    protected final By accountLink = By.cssSelector("a[href='/account']");
    protected final By tabCurrent = By.cssSelector("div[class*='current']");
    protected final  By profileLink = By.xpath("//a[contains(@href,'/account/profile')]");

    public HomeAuthPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get accessToken")
    public String getToken() throws InterruptedException {
        return getAccessToken(locatorAccessToken);
    }

    @Step("Check")
    public void checkStatusContentLoginAccount() {
        checkLocator(userStatusContent);
    }

    @Step("Click on the link")
    public ProfilePage clickAccountLinkRegisterUser() {
        waitOverlayDisappear();
        waitLocator(userStatusContent);
        waitOverlayDisappear();
        WebElement element = waitClickable(accountLink);
        waitOverlayDisappear();
        element.click();
        waitLocator(profileLink);

        return new ProfilePage(driver);
    }

    @Step("Get current tab")
    public WebElement getCurrentTab() {
        checkLocator(tabCurrent);

        return waitClickable(tabCurrent);
    }

    @Step("Check current tab (tabCurrent)")
    public void checkCurrentTab(String tabName) throws InterruptedException {
        By tabLocator = By.xpath("//div[contains(@class,'tab')][.//span[text()='" + tabName + "']]");
        By tabTitle = By.xpath("//h2[contains(@class,'text_type_main-medium') and text()='" + tabName + "']");
        waitOverlayDisappear();

        WebElement element = waitClickable(tabLocator);
        goToElement(element);
        element = waitClickable(tabLocator);
        String classValue = element.getAttribute("class");
        if (!classValue.contains("tab_tab_type_current")) {
            waitOverlayDisappear();
            element.click();
            new WebDriverWait(driver, EXPLICIT_TIMEOUT)
                    .until(d -> waitClickable(tabLocator).getAttribute("class").contains("tab_tab_type_current"));
        }
        checkLocator(tabTitle);
//        String value = element.getAttribute("class");
//        assertTrue(value.contains("tab_tab_type_current"));
        String actualValue = element.findElement(By.tagName("span")).getText();
        assertEquals(tabName, actualValue);
    }


    public void clickCurrentTab(String tabName) {
        By tabXpath = By.xpath("//div[contains(@class,'tab')][.//span[text()='" + tabName + "']]");
        checkLocator(tabXpath);

        WebElement element = waitClickable(tabXpath);
        waitOverlayDisappear();
        String classValue = element.getAttribute("class");

        if (!classValue.contains("tab_tab_type_current")) {
            element.click();
        }
    }

}
