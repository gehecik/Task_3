package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    protected final By profileLink = By.xpath("//a[contains(@href,'/account/profile')]");
    protected final By designerLink = By.xpath("//a[@href='/' and contains(@class,'AppHeader_header__link__3D_hX')]");
    protected final By logoLink = By.xpath("//div[contains(@class,'AppHeader_header__logo__2D0X2')]//a");
    protected final By logoutButton = By.xpath("//button[text()='Выход']");//By.xpath("//button[contains(@class,'Account_button__14Yp3')]");
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Check")
    public void checkProfilePage() {
        waitLocator(profileLink);

        checkLocator(profileLink);
    }

    @Step("Click on the link")
    public HomeAuthPage clickDesigner() {
        //checkLocator(designerLink);
        waitClickable(designerLink).click();

        return new HomeAuthPage(driver);
    }

    @Step("Click on the link")
    public HomeAuthPage clickLogo() {
        waitClickable(logoLink).click();

        return new HomeAuthPage(driver);
    }

    @Step("Click on the button")
    public LoginPage clickLogout() {
        checkLocator(logoutButton);
        waitClickable(logoutButton).click();

        return new LoginPage(driver);
    }


}
