package org.example.pages.user;

import io.qameta.allure.Step;
import org.example.data.User;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.utils.EnvConfig.BASE_URL;
import static org.example.utils.EnvConfig.EXPLICIT_TIMEOUT;

public class LoginPage extends BasePage {
    protected final By registerLink = By.xpath("//a[contains(@href, '/register')]");
    protected final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    protected final By logoutButton = By.xpath("//button[contains(text(),'Выйти')]");
    protected final By inputEmail = By.xpath("//label[text()='Email']/following-sibling::input");
    protected final By inputPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");
    protected final By userStatusContent = By.className("Auth_login__3hAey");
    protected final By forgotPasswordLink = By.xpath("//a[contains(@href, '/forgot-password')]");
    protected final By personAccount = By.xpath("//p[contains(text(),'Личный Кабинет')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click on the link (registerLink)")
    public RegisterPage clickRegisterLink() {
        waitClickable(registerLink).click();

        return new RegisterPage(driver);
    }

    @Step("Click on the link (forgotPasswordLink)")
    public PasswordRecoveryPage clickForgotPasswordLink() {
        waitClickable(forgotPasswordLink).click();

        return new PasswordRecoveryPage(driver);
    }

    @Step("Check")
    public void checkUserData(By locator, String field) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String value = element.getAttribute("value");

        if (value == null || value.isEmpty()) {
            enterNewValue(locator, field);
        }
    }

    @Step("Click on the button (loginButton)")
    public HomeAuthPage clickLogin(User user) throws InterruptedException {
        waitLocator(inputEmail);
        enterNewValue(inputEmail, user.getEmail());
        waitLocator(inputPassword);
        enterNewValue(inputPassword, user.getPassword());

        waitClickable(loginButton).click();
        waitLocator(personAccount);

        return new HomeAuthPage(driver);
    }

    @Step("Check")
    public void checkStatusContent() {
        checkLocator(userStatusContent);
    }

    public void openHomePage() {
        driver.get(BASE_URL);
    }

}
