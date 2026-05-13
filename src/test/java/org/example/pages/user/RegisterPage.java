package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.data.User;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
    protected final By inputName = By.xpath("//label[contains(@class,'input__placeholder') and text()='Имя']/following-sibling::input");
    protected final By inputEmail = By.xpath("//label[contains(@class,'input__placeholder') and text()='Email']/following-sibling::input");
    protected final By inputPassword = By.xpath("//label[contains(@class,'input__placeholder') and text()='Пароль']/following-sibling::input");
    protected final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    protected final By wrongPasswordStatusContent = By.className("input__error");
    protected final By loginLink = By.cssSelector("a[href='/login']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Input user fields")
    public void registerUser(User user) {
        enterNewValue(inputName, user.getName());
        enterNewValue(inputEmail, user.getEmail());
        enterNewValue(inputPassword, user.getPassword());
    }

    @Step("Click on the register button")
    public LoginPage clickRegisterButton() {
        //waitLocator(registerButton);
        waitClickable(registerButton).click();

        return new LoginPage(driver);
    }

    @Step("Check")
    public void checkWithWrongPassword() {
        waitLocator(registerButton);
        waitClickable(registerButton).click();

        checkLocator(wrongPasswordStatusContent);
    }

    @Step("Click on the link")
    public LoginPage clickLoginLink() {
        checkLocator(loginLink);
        waitClickable(loginLink).click();

        return new LoginPage(driver);
    }
}
