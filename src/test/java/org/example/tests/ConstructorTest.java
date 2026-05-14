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

public class ConstructorTest {
    @RegisterExtension
    private final DriverExtension extension = new DriverExtension();
    ActionsUser actionsUser = new ActionsUser();
    private User user;
    String accessToken;
    private HomePage homePage;

    private WebDriver driver;



    @BeforeEach
    public void createAndLoginUser() throws InterruptedException {
        RestAssured.baseURI = BASE_URL;
        user = User.userWithRandomField();
        Response response = actionsUser.createUser(user);
        accessToken = response.path("accessToken");
        actionsUser.loginUser(user);

        driver = extension.getDriver();
        homePage = new HomePage(driver);
        homePage.openPage();

        homePage.setAccessToken(accessToken);
        driver.navigate().to(BASE_URL);
        Thread.sleep(5000);
    }

    @Test
    @DisplayName("Go from personal account to Designer")
    @Description("Check the transition by clicking on \"Designer\"")
    public void openPersonalAccount() throws InterruptedException {
        var profilePage = homePage.clickAccountLinkUser();
        profilePage.checkProfilePage();
    }

    @Test
    @DisplayName("Go to personal account")
    @Description("Check the transition by clicking on the Stellar Burgers logo")
    public void openPersonalAccountClickLogo() throws InterruptedException {
        var profilePage = homePage.clickAccountLinkUser();
        Thread.sleep(5000);
        var homeAuthPage = profilePage.clickLogo();
        homeAuthPage.checkStatusContentLoginAccount();
    }

    @AfterEach
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            actionsUser.deleteByToken(accessToken);
        }
    }

}
