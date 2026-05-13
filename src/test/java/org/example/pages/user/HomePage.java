package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.utils.EnvConfig.BASE_URL;

public class HomePage extends BasePage{
    protected final By accountLink = By.xpath("//a[contains(@href,'/account')]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(BASE_URL);
    }

    public LoginPage clickAccountLink() {
        waitClickable(accountLink).click();

        return new LoginPage(driver);
    }

}
