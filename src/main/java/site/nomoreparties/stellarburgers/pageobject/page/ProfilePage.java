package site.nomoreparties.stellarburgers.pageobject.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;

    private final By profileTabButton = By.xpath(".//a[text()='Профиль']");
    private final By exitTabButton = By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка достпуности поля Профиль")
    public boolean isProfileTabEnable() {
        return driver.findElement(profileTabButton)
                .isEnabled();
    }

    @Step("Клик по кнопке выхода")
    public void clickExitButton() {
        driver.findElement(exitTabButton)
                .click();
    }
}
