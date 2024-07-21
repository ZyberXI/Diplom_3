package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.constants.ApiEnum;
import site.nomoreparties.stellarburgers.pageobject.page.MainPage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class FromProfileTest {

    private WebDriver driver;
    private final String button;

    @Before
    @Step("Подготовка")
    public void setUp() {
        driver = DriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(DriverConfig.TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(ApiEnum.BASE_URL);
    }

    public FromProfileTest(String button) {
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[] backToMainButtons() {
        return new Object[][]{
                {ApiEnum.LOGO_PATH},
                {ApiEnum.MAIN_PAGE_PATH},
        };
    }

    @Test
    public void fromProfileToMain() {
        MainPage mainPage = new MainPage(driver);
        mainPage.checkLoginButtonEnable();
        mainPage.clickProfileButton();
        mainPage.goToMainPage(button);
        Asserts.assertCreateBurgerText(driver, "Соберите бургер");
    }

    @After
    @Step("Выход")
    public void quit() {
        driver.quit();
    }

}
