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

public class LogoutTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();
    ActionsUser actionsUser = new ActionsUser();
    private User user;
    String accessToken;
    private HomePage homePage;

    private WebDriver driver;

    @BeforeEach
    public void createUser() throws InterruptedException {
        RestAssured.baseURI = BASE_URL;
        user = User.userWithRandomField();
        Response response = actionsUser.createUser(user);
        accessToken = response.path("accessToken");

        driver = extension.getDriver();
        homePage = new HomePage(driver);
        homePage.openPage();
    }

    @Test
    @DisplayName("Check logout")
    @Description("Check logout")
    public void openPersonalAccount() throws InterruptedException {
        var loginPage = homePage.clickLoginToAccount();
        var homeAuthPage = loginPage.clickLogin(user);
        //Thread.sleep(5000);
        var profilePage = homeAuthPage.clickAccountLinkRegisterUser();
        loginPage = profilePage.clickLogout();
        loginPage.checkStatusContent();
    }


    @AfterEach
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            actionsUser.deleteByToken(accessToken);
        }
    }
}
