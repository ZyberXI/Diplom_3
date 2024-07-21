package site.nomoreparties.stellarburgers.pageobject.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class ResetPasswordPage {
    WebDriver driver;

    private final By loginButtonFromResetForm = By.xpath("//a[@href='/login']");

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке логин на форме сброса пароля")
    public void clickLoginButtonFromResetForm() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(loginButtonFromResetForm));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(loginButtonFromResetForm).click();
    }
}
