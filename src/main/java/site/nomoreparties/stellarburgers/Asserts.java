package site.nomoreparties.stellarburgers;

import io.qameta.allure.Step;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.pageobject.page.LoginPage;
import site.nomoreparties.stellarburgers.pageobject.page.MainPage;
import site.nomoreparties.stellarburgers.pageobject.page.RegistrationPage;

import static org.hamcrest.Matchers.equalTo;

public class Asserts {

    @Step("Проверка что в header'e текст имеет '{expectedHeaderText}'")
    public static void assertHeaderText(WebDriver driver, String expectedHeaderText) {
        LoginPage loginPage = new LoginPage(driver);
        MatcherAssert.assertThat(loginPage.getHeaderText(), equalTo(expectedHeaderText));
    }

    @Step("Проверка что в header'e текст имеет '{expectedBuregerText}'")
    public static void assertCreateBurgerText(WebDriver driver, String expectedBuregerText) {
        MainPage mainPage = new MainPage(driver);
        MatcherAssert.assertThat(mainPage.getBurgerHeader(), equalTo(expectedBuregerText));
    }

    @Step("Проверка сообщения об ошибки, имеет текст '{expectedErrorPasswordText}'")
    public static void assertInvalidPasswordText(WebDriver driver, String expectedErrorPasswordText) {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        MatcherAssert.assertThat(registrationPage.getInvalidPasswordText(), equalTo(expectedErrorPasswordText));
    }
}
