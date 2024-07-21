package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class UserRandom {
    private static final String DOMAIN_NAME = "@gmail.com";
    public static String randomUserName() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public static String randomUserEmail() {
        return RandomStringUtils.randomAlphabetic(10) + DOMAIN_NAME;
    }

    public static String randomUserPassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    @Step("Генерация данных пользователя")
    public static UserData createRandomUser() {
        String name = randomUserName();
        String email = randomUserEmail();
        String password = randomUserPassword();
        return new UserData(name, email, password);
    }
}
