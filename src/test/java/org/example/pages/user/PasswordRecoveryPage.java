package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage extends BasePage {
    protected final By loginLink = By.cssSelector("a[href='/login']");

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the link")
    public LoginPage clickLoginLinkRecoveryPage() {
        checkLocator(loginLink);
        waitClickable(loginLink).click();

        return new LoginPage(driver);
    }
}
