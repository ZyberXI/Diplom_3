package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.constants.ApiEnum;
import site.nomoreparties.stellarburgers.pageobject.page.LoginPage;
import site.nomoreparties.stellarburgers.pageobject.page.MainPage;
import site.nomoreparties.stellarburgers.pageobject.page.ProfilePage;
import site.nomoreparties.stellarburgers.user.UserApi;
import site.nomoreparties.stellarburgers.user.UserData;
import site.nomoreparties.stellarburgers.user.UserRandom;

import java.util.concurrent.TimeUnit;

public class ToProfileTest {
    private WebDriver driver;
    UserData userData;

    @Before
    @Step("Подготовка")
    public void setUp() {
        RestAssured.baseURI = ApiEnum.BASE_URL;
        userData = UserRandom.createRandomUser();
        UserApi.createUser(userData);
        driver = DriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(DriverConfig.TIMEOUT, TimeUnit.SECONDS);
    }

    @DisplayName("Попытка входа в аккаунт как неавторизированный пользователь")
    @Test
    public void authUserGetProfileTest() {
        driver.navigate().to(ApiEnum.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginButton();
        loginPage.loginUser(userData);
        loginPage.clickProfileButton();

        Assert.assertTrue(profilePage.isProfileTabEnable());
    }

    @DisplayName("Попытка входа в аккаунт как неавторизированный пользователь")
    @Test
    public void nonAuthUserGetProfileTest() {
        driver.navigate().to(ApiEnum.BASE_URL);

        MainPage mainPage = new MainPage(driver);

        mainPage.checkLoginButtonEnable();
        mainPage.clickProfileButton();

        Asserts.assertHeaderText(driver, "Вход");
    }

    @After
    @Step("Чистка")
    public void quit() {
        UserApi.deleteUser(UserApi.getAuthToken(userData));
        driver.quit();
    }
}
