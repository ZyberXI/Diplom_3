package site.nomoreparties.stellarburgers.user;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.nomoreparties.stellarburgers.constants.ApiEnum;

import static io.restassured.RestAssured.given;

public class UserApi {
    @Step("Создание пользователя")
    public static ValidatableResponse createUser(UserData userData) {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(userData)
                .when()
                .post(ApiEnum.USER_REGISTER_PATH).then();
        return response;
    }

    @Step("Логин пользователя")
    public static ValidatableResponse loginUser(UserData userData) {
        ValidatableResponse response = given()
                .header("Content-type", "application/json")
                .and()
                .body(userData)
                .when()
                .post(ApiEnum.USER_LOGIN_PATH).then();
        return response;
    }

    @Step("Получение токена")
    public static String getAuthToken(UserData userData) {
        return loginUser(userData)
                .extract()
                .path("accessToken");
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String authToken) {
        if (authToken != null) {
            given()
                    .header("Authorization", authToken)
                    .when()
                    .delete(ApiEnum.USER_INF_PATH).then();
        }
    }
}
