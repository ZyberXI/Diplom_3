package site.nomoreparties.stellarburgers.pageobject.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.constants.ApiEnum;

public class MainPage {
    WebDriver driver;

    private final By logoButton= By.className("AppHeader_header__logo__2D0X2");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By createBurgerHeader = By.xpath(".//h1[text()='Собери бургер']");
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By bunsButton= By.xpath(".//span[text()='Булки']");
    private final By currentBunButton = By.xpath(".//div[(contains(span/text(),'Булки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    private final By sauceButton = By.xpath(".//span[text()='Соусы']");
    private final By currentSauceButton = By.xpath(".//div[(contains(span/text(),'Соусы')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");
    private final By currentFillingsButton = By.xpath(".//div[(contains(span/text(),'Начинки')) and (contains(@class, 'tab_tab_type_current__2BEPc'))]");
    private final By profileButton = By.xpath(".//p[text()='Личный кабинет']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по лого")
    public void clickLogoButton() {
        driver.findElement(logoButton)
                .click();
    }

    @Step("Клик по кнопке конструктора")
    public void clickConstructor() {
        driver.findElement(constructorButton)
                .click();
    }

    @Step("Клик по кнопке логина")
    public void clickLoginButton() {
        driver.findElement(loginButton)
                .click();
    }

    @Step("Проверка видимости кнопки войти")
    public void checkLoginButtonEnable() {
        driver.findElement(profileButton)
                .isEnabled();
    }

    @Step("Клик по булочкам")
    public void clickBunsButton() {
        driver.findElement(bunsButton)
                .click();
    }

    @Step("Клик по соусам")
    public void clickSauceButton() {
        driver.findElement(sauceButton)
                .click();
    }

    @Step("Клик по начинкам")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton)
                .click();
    }

    @Step("Получение header'a бургера")
    public String getBurgerHeader () {
        return driver.findElement(createBurgerHeader)
                .getText();
    }

    @Step("Проверка доступности булочек")
    public boolean isBunsEnable() {
        return driver.findElement(currentBunButton)
                .isEnabled();
    }

    @Step("Проверка доступности соусов")
    public boolean isSauceEnable() {
        return driver.findElement(currentSauceButton)
                .isEnabled();
    }

    @Step("Проверка доступности начинок")
    public boolean isFiillingsEnable() {
        return driver.findElement(currentFillingsButton)
                .isEnabled();
    }

    @Step("Клик по кнопке профиля")
    public void clickProfileButton() {
        driver.findElement(profileButton)
                .click();
    }

    @Step("Возврат к домашней странице")
    public void goToMainPage(String button) {
        if (button.equals(ApiEnum.LOGO_PATH)) {
            clickLogoButton();
        } else {
            clickConstructor();
        }
    }
}
