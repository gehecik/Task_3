package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.utils.EnvConfig.BASE_URL;

public class HomePage extends BasePage {
    protected final By accountLink = By.cssSelector("a[href='/account']");
    protected final By loginToAccount = By.xpath("//button[text()='Войти в аккаунт']");
    protected final By loginToAccount1 = By.cssSelector("button.button_button__33qZ0");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(BASE_URL);
    }
    public void openPageAndWait() {
        openPage();
        waitLocator(loginToAccount1);
    }

    @Step("Click on the link (accountLink)")
    public LoginPage clickAccountLink() throws InterruptedException {
        waitOverlayDisappear();
        waitLocator(accountLink);
        waitOverlayDisappear();
        waitClickable(accountLink).click();

        return new LoginPage(driver);
    }

    @Step("Click on the button (loginToAccount)")
    public LoginPage clickLoginToAccount() throws InterruptedException {
        waitOverlayDisappear();
        waitLocator(loginToAccount);
        waitOverlayDisappear();
        waitClickable(loginToAccount).click();

        return new LoginPage(driver);
    }

    @Step("Check")
    public void checkHomePage() {
        checkLocator(loginToAccount);
    }

    @Step("Click on the link (accountLinkUser)")
    public ProfilePage clickAccountLinkUser() throws InterruptedException {
        waitOverlayDisappear();
        waitLocator(accountLink);
        waitOverlayDisappear();
        waitClickable(accountLink).click();

        return new ProfilePage(driver);
    }

}
