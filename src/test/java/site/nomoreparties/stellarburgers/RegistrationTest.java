package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.constants.ApiEnum;
import site.nomoreparties.stellarburgers.pageobject.page.RegistrationPage;
import site.nomoreparties.stellarburgers.user.UserApi;
import site.nomoreparties.stellarburgers.user.UserData;
import site.nomoreparties.stellarburgers.user.UserRandom;

import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    private WebDriver driver;

    UserData userData;

    @Before
    @Step("Подготовка")
    public void setUp() {
        userData = UserRandom.createRandomUser();
        driver = DriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(DriverConfig.TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(ApiEnum.USER_REGISTER_PATH);
    }

    @DisplayName("Проверка ошибки при передаче 5 символьного пароля")
    @Test
    public void fiveSymbolsInPasswTest() throws InterruptedException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        String password = userData.getPassword().substring(0, Math.min(userData.getPassword().length(), 5));

        registrationPage.setName(userData.getName());
        registrationPage.setEmail(userData.getEmail());
        registrationPage.setPassword(password);
        registrationPage.clickRegButton();

        Thread.sleep(3000);

        Asserts.assertInvalidPasswordText(driver, "Некорректный пароль");
    }

    @After
    public void quit() {
        if (authToken != null && userData != null) {
            UserApi.deleteUser(authToken);
            driver.quit();
        }
    }
}
