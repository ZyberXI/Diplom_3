package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.constants.ApiEnum;
import site.nomoreparties.stellarburgers.pageobject.page.MainPage;

import java.util.concurrent.TimeUnit;

public class ConstructorTest {
    private WebDriver driver;

    @Before
    @Step("Подготовка")
    public void setUp() {
        driver = DriverConfig.setDriver();
        driver.manage().timeouts().implicitlyWait(DriverConfig.TIMEOUT, TimeUnit.SECONDS);
        driver.navigate().to(ApiEnum.BASE_URL);
    }

    @DisplayName("Переход из вкладки Булочки и возврат на вкладку Булочки")
    @Test
    public void changeBunTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        mainPage.clickBunsButton();
        Assert.assertTrue(mainPage.isBunsEnable());
    }

    @DisplayName("Переход на вкладку соусы")
    @Test
    public void saucesTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauceButton();
        Assert.assertTrue(mainPage.isSauceEnable());
    }

    @DisplayName("Переход на вкладку начинки")
    @Test
    public void fillingsTabTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsButton();
        Assert.assertTrue(mainPage.isFiillingsEnable());
    }

    @After
    @Step("Выход")
    public void quit() {
        driver.quit();
    }

}
