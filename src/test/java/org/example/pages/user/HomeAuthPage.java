package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeAuthPage extends BasePage{

    protected final By locatorAccessToken = By.xpath("//p[text()='Личный Кабинет']");

    public HomeAuthPage(WebDriver driver) {
        super(driver);
    }

    public String getToken() throws InterruptedException {
        return getAccessToken(locatorAccessToken);
    }
}
