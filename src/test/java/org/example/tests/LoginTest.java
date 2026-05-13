package org.example.tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.api.ActionsUser;
import org.example.data.User;
import org.example.pages.user.HomePage;
import org.example.utils.DriverExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import static org.example.utils.EnvConfig.BASE_URL;

public class LoginTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();
    ActionsUser actionsUser = new ActionsUser();
    private User user;
    String accessToken;
    private HomePage homePage;
    private WebDriver driver;

    @BeforeEach
    public void createUser() {
        RestAssured.baseURI = BASE_URL;
        user = User.userWithRandomField();
        Response response = actionsUser.createUser(user);
        accessToken = response.path("accessToken");

        driver = extension.getDriver();
        homePage = new HomePage(driver);
        homePage.openPage();
    }

    @Test
    @DisplayName("Log in to account")
    @Description("login by clicking on the \"Log in to account\" button on the main")
    public void loginFromLoginAccountSuccessfully() throws Exception {
        var loginPage = homePage.clickLoginToAccount();
        var homeAuthPage = loginPage.clickLogin(user);
        homeAuthPage.checkStatusContentLoginAccount();
    }

    @Test
    @DisplayName("Log in to personal account")
    @Description("login by clicking on the \"Personal account\" button")
    public void loginFromPersonalAccountSuccessfully() throws Exception {
        var loginPage = homePage.clickAccountLink();
        var homeAuthPage = loginPage.clickLogin(user);
        homeAuthPage.checkStatusContentLoginAccount();
    }

    @Test
    @DisplayName("Log in by registration form")
    @Description("login by clicking on the button in the registration form")
    public void loginFromRegistrationFormSuccessfully() throws Exception {
        var loginPage = homePage.clickAccountLink();
        var registerPage = loginPage.clickRegisterLink();
        loginPage = registerPage.clickLoginLink();
        var homeAuthPage = loginPage.clickLogin(user);
        homeAuthPage.checkStatusContentLoginAccount();
    }

    @Test
    @DisplayName("Log in by password recovery form")
    @Description("login by clicking on the button in the password recovery form.")
    public void loginFromForgotPasswordSuccessfully() throws Exception {
        var loginPage = homePage.clickAccountLink();
        var passwordRecoveryPage = loginPage.clickForgotPasswordLink();
        loginPage = passwordRecoveryPage.clickLoginLinkRecoveryPage();
        var homeAuthPage = loginPage.clickLogin(user);
        homeAuthPage.checkStatusContentLoginAccount();
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            actionsUser.deleteByToken(accessToken);
        }
    }
}
