package org.example;

import org.example.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.example.utils.EnvConfig.BASE_URL;

public class RegisterPage extends BasePage {
    protected final By inputName = By.xpath("//label[contains(@class,'input__placeholder') and text()='Имя']/following-sibling::input");
    protected final By inputEmail = By.xpath("//label[contains(@class,'input__placeholder') and text()='Email']/following-sibling::input");
    protected final By inputPassword = By.xpath("//label[contains(@class,'input__placeholder') and text()='Пароль']/following-sibling::input");
    protected final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    protected final By wrongPasswordStatusContent = By.className("input__error");
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(User user) {
        enterNewValue(inputName, user.getName());
        enterNewValue(inputEmail, user.getEmail());
        enterNewValue(inputPassword, user.getPassword());
    }

    public LoginPage clickRegisterButton() {
        waitClickable(registerButton).click();

        //waitURL(BASE_URL + "/login");
        return new LoginPage(driver);
    }

    public void checkWithWrongPassword() {
        waitClickable(registerButton).click();

        checkLocator(wrongPasswordStatusContent);
    }

}
