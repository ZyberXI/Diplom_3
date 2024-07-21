package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.constants.ApiEnum;
import site.nomoreparties.stellarburgers.pageobject.page.*;
import site.nomoreparties.stellarburgers.user.UserApi;
import site.nomoreparties.stellarburgers.user.UserData;
import site.nomoreparties.stellarburgers.user.UserRandom;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
    private WebDriver driver;
    UserData userData;

    @Before
    @Step("Подготовка")
    public void setUp() {
        RestAssured.baseURI = ApiEnum.BASE_URL;
        userData = UserRandom.createRandomUser();
        System.out.println(userData);
        UserApi.createUser(userData);
        driver = DriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(DriverConfig.TIMEOUT, TimeUnit.SECONDS);
    }

    @DisplayName("Логин пользователя через кнопку Войти на основной странице")
    @Test
    public void loginWithMainButtonTest() {
        driver.navigate().to(ApiEnum.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginButton();
        loginPage.loginUser(userData);
        loginPage.clickProfileButton();

        Assert.assertTrue(profilePage.isProfileTabEnable());
    }

    @DisplayName("Логин пользвоателя через форму профиля")
    @Test
    public void loginWithAccountButtonTest() {
        driver.navigate().to(ApiEnum.BASE_URL);

        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.clickProfileButton();
        loginPage.loginUser(userData);
        loginPage.clickProfileButton();

        Assert.assertTrue(profilePage.isProfileTabEnable());
    }

    @DisplayName("Логин пользвоателя через форму регистрации нового пользователя")
    @Test
    public void loginFromRegFormTest() {
        driver.navigate().to(ApiEnum.USER_REGISTER_PATH);

        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        registrationPage.clickLoginButtonFromNewRegForm();
        loginPage.loginUser(userData);
        loginPage.clickProfileButton();

        Assert.assertTrue(profilePage.isProfileTabEnable());
    }

    @DisplayName("Логин пользователя через форму сброса пароля")
    @Test
    public void loginFromResetFormTest() {
        driver.navigate().to(ApiEnum.RESET_PASSW_PATH);

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        resetPasswordPage.clickLoginButtonFromResetForm();
        loginPage.loginUser(userData);
        loginPage.clickProfileButton();

        Assert.assertTrue(profilePage.isProfileTabEnable());
    }

    @DisplayName("Выход из аккаунта")
    @Test
    public void logOutTest() {
        driver.navigate().to(ApiEnum.BASE_URL);

        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        loginPage.clickProfileButton();
        loginPage.loginUser(userData);
        loginPage.clickProfileButton();
        profilePage.clickExitButton();

        MatcherAssert.assertThat(loginPage.getHeaderText(), equalTo("Вход"));
    }

    @After
    @Step("Чистка")
    public void quit() {
        String authToken = UserApi.getAuthToken(userData);
        if (authToken != null) {
            UserApi.deleteUser(authToken);
        }
        driver.quit();
    }
}

