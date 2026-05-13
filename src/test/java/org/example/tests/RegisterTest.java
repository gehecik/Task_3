package org.example;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.example.api.ActionsUser;
import org.example.data.User;
import org.example.utils.DriverExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import static org.example.utils.EnvConfig.BASE_URL;

public class RegisterTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();
    ActionsUser actionsUser = new ActionsUser();

    String accessToken;

    @Test
    @DisplayName("Successful registration")
    @Description("Successful registration")
    public void createUserSuccessfully() throws Exception {
        WebDriver driver = extension.getDriver();
        User user = User.userWithRandomField();

        var homePage = new HomePage(driver);
        homePage.openPage();
        homePage.openPage();
        var loginPage = homePage.clickAccountLink();
        var registerPage = loginPage.clickRegisterLink();
        registerPage.registerUser(user);
        var returnLoginPage = registerPage.clickRegisterButton();
        returnLoginPage.checkStatusContent();

        var homeAuthPage = returnLoginPage.clickLogin(user);
        accessToken = homeAuthPage.getToken();
    }

    @Test
    @DisplayName("Invalid password error")
    @Description("Invalid password error. The minimum password is six characters.")
    public void createUserWithWrongPassword() throws Exception {
        WebDriver driver = extension.getDriver();
        User user = User.userWithWrongPassword();

        var homePage = new HomePage(driver);
        homePage.openPage();
        var loginPage = homePage.clickAccountLink();
        var registerPage = loginPage.clickRegisterLink();
        registerPage.registerUser(user);
        registerPage.checkWithWrongPassword();
    }

    @AfterEach
    public void tearDown() {
        RestAssured.baseURI = BASE_URL;
        if (accessToken != null && !accessToken.isEmpty()) {
            actionsUser.deleteByToken(accessToken);
        }
    }
}
