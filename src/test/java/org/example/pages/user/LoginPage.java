package org.example;

import org.example.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.utils.EnvConfig.BASE_URL;
import static org.example.utils.EnvConfig.EXPLICIT_TIMEOUT;

public class LoginPage extends BasePage{
    protected final By registerLink = By.xpath("//a[contains(@href, '/register')]");
    protected final By loginButton = By.xpath("//button[contains(text(),'Войти')]");
    protected final By logoutButton = By.xpath("//button[contains(text(),'Выйти')]");
    protected final By inputEmail = By.xpath("//label[contains(@class,'input__placeholder') and text()='Email']/following-sibling::input");
    protected final By inputPassword = By.xpath("//label[contains(@class,'input__placeholder') and text()='Пароль']/following-sibling::input");//By.xpath("//input[@type='password']");
    protected final By userStatusContent = By.className("Auth_login__3hAey");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public RegisterPage clickRegisterLink() {
        waitClickable(registerLink).click();

        return new RegisterPage(driver);
    }

    public void checkUserData(By locator, String field) {
        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String value = element.getAttribute("value");

        if (value == null || value.isEmpty()) {
            enterNewValue(locator, field);
        }
    }

    public HomeAuthPage clickLogin(User user) throws InterruptedException {
//        checkUserData(inputEmail, user.getEmail());
//        checkUserData(inputPassword, user.getPassword());
        Thread.sleep(5000);
        waitLocator(inputEmail);
        enterNewValue(inputEmail, user.getEmail());
        waitLocator(inputPassword);
        enterNewValue(inputPassword, user.getPassword());
        WebElement loginBtn = waitClickable(loginButton);
        loginBtn.click();

        //waitPage("account", logoutButton);
        //waitLocator();
        return new HomeAuthPage(driver);
    }

    public void checkStatusContent() {
        checkLocator(userStatusContent);
    }

}
