package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import static org.example.utils.EnvConfig.BASE_URL;

public class HomePage extends BasePage {
    protected final By accountLink = By.cssSelector("a[href='/account']");
    protected final By loginToAccount = By.xpath("//button[text()='Войти в аккаунт']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(BASE_URL);
    }
    public void openPageAndWait() {
        openPage();
        waitLocator(accountLink);
    }


    @Step("Click on the link (accountLink)")
    public LoginPage clickAccountLink() {
        //waitLocator(accountLink);
        waitClickable(accountLink).click();

        return new LoginPage(driver);
    }

    @Step("Click on the button (loginToAccount)")
    public LoginPage clickLoginToAccount() {
        //waitLocator(loginToAccount);
        waitClickable(loginToAccount).click();

        return new LoginPage(driver);
    }

    @Step("Check")
    public void checkHomePage() {
        checkLocator(loginToAccount);
    }

    @Step("Click on the link (accountLinkUser)")
    public ProfilePage clickAccountLinkUser() {
        //waitLocator(accountLink);
        waitClickable(accountLink).click();

        return new ProfilePage(driver);
    }

}
