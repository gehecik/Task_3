package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {
    protected final By profileLink = By.xpath("//a[contains(@href,'/account/profile')]");
    protected final By designerLink = By.xpath("//a[@href='/' and contains(@class,'AppHeader_header__link__3D_hX')]");
    protected final By logoLink = By.xpath("//div[contains(@class,'AppHeader_header__logo__2D0X2')]//a");
    protected final By logoutButton = By.xpath("//button[text()='Выход']");
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check")
    public void checkProfilePage() {
        waitLocator(profileLink);

        checkLocator(profileLink);
    }

    @Step("Click on the link (designerLink)")
    public HomeAuthPage clickDesigner() {
        checkLocator(designerLink);
        waitOverlayDisappear();
        waitClickable(designerLink).click();

        return new HomeAuthPage(driver);
    }

    @Step("Click on the link (logoLink)")
    public HomeAuthPage clickLogo() {
        waitOverlayDisappear();
        waitLocator(logoLink);
        waitOverlayDisappear();
        WebElement element = waitClickable(logoLink);
        waitOverlayDisappear();
        element.click();

        return new HomeAuthPage(driver);
    }

    @Step("Click on the button (logoutButton)")
    public LoginPage clickLogout() {
        checkLocator(logoutButton);
        waitOverlayDisappear();
        WebElement element = waitClickable(logoutButton);
        waitOverlayDisappear();
        element.click();

        return new LoginPage(driver);
    }

}
