package site.nomoreparties.stellarburgers.pageobject.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class RegistrationPage {
    WebDriver driver;

    private final By inputName = By.xpath(".//label[text()='Имя']/../input");
    private final By inputEmail = By.xpath(".//label[text()='Email']/../input");
    private final By inputPassword = By.xpath(".//label[text()='Пароль']/../input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By invalidPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginFromNewRegFormButton = By.className("Auth_link__1fOlj");

    public RegistrationPage (WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени пользователя")
    public void setName (String name) {
        driver.findElement(inputName)
                .sendKeys(name);
    }

    @Step("Ввод email'a пользователя")
    public void setEmail (String email) {
        driver.findElement(inputEmail)
                .sendKeys(email);
    }

    @Step("Ввод пароля пользователя")
    public void setPassword (String password) {
        driver.findElement(inputPassword)
                .sendKeys(password);
    }

    @Step("Клик по кнопке регистрации")
    public void clickRegButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(registerButton));
        driver.findElement(registerButton).click();
    }

    @Step("Получение сообщения о недопустимости пароля")
    public String getInvalidPasswordText() {
        return driver.findElement(invalidPasswordText)
                .getText();
    }

    @Step("Клик по кнопке войти на форме регистрации")
    public void clickLoginButtonFromNewRegForm() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(loginFromNewRegFormButton));
        driver.findElement(loginFromNewRegFormButton).click();
    }
}
