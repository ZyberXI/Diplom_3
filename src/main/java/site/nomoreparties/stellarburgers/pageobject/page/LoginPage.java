package site.nomoreparties.stellarburgers.pageobject.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import site.nomoreparties.stellarburgers.user.UserData;

public class LoginPage {
    WebDriver driver;
    private final By headerLogin = By.xpath(".//h2[text()='Вход']");
    private final By inputEmail = By.xpath(".//label[text()='email']/../input");
    private final By inputPassword = By.xpath(".//label[text()='password']/../input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By profileButton = By.xpath(".//p[text()='Личный кабинет']");

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка доступности кнопки создания заказа")
    public boolean isCreateOrderButtonEnable() {
        return driver.findElement(createOrderButton)
                .isEnabled();
    }

    @Step("Получение текста из header'a")
    public String getHeaderText() {
        return driver.findElement(headerLogin)
                .getText();
    }

    @Step("Ввод поля email")
    public void insertEmail(String email) {
        WebElement emailInput = driver.findElement(inputEmail);
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    @Step("Ввод поля password")
    public void insertPassword(String password) {
        WebElement passwordInput = driver.findElement(inputPassword);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    @Step("Клик по кнопке войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке профиля")
    public void clickProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Проверка успешной авторизации")
    public void loginUser(UserData userData) {
        insertEmail(userData.getEmail());
        insertPassword(userData.getPassword());
        clickLoginButton();
        Assert.assertTrue(isCreateOrderButtonEnable());
    }

}
