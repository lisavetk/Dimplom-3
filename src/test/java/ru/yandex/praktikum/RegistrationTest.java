package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.api.LoginUserRequest;
import ru.yandex.praktikum.api.UsersSteps;
import ru.yandex.praktikum.helpers.UserGenerator;
import ru.yandex.praktikum.page.objects.RegistrationPage;

import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.helpers.TestConstants.*;

@DisplayName("Тесты на регистрацию пользователя")
public class RegistrationTest extends BaseTest {
     private RegistrationPage registrationPage;

     String email;
     String password;
     UsersSteps usersSteps = new UsersSteps();
     String accessToken;

    @Before
    public void setUp() {
        super.setUp();

        registrationPage = new RegistrationPage(driver);
        registrationPage.goToRegistrationPage();
    }

    @DisplayName("Тест на успешную регистрацию пользователя")
    @Description("Вводятся корректные данные имени, почты и пароля")
    @Test
    public void shouldRegisterSuccessfullyWithValidData() {
        email = UserGenerator.getRandomEmail();
        password = UserGenerator.getRandomPassword(6);
        registrationPage.enterFormRegistration(UserGenerator.getRandomName(), email, password);
        registrationPage.clickButtonRegistrationOnRegistrationPage();
        assertEquals(MESSAGE_TITLE_LOGIN_AFTER_REGISTRATION, TITLE_AUTH_PAGE, registrationPage.getTextLoginTitle());

    }

    @DisplayName("Тест на непрошедшую регистрацию пользователя")
    @Description("Вводятся корректные данные имени, почты. Пароль указывается 5 символов")
    @Test
    public void shouldShowErrorWhenPasswordIsTooShort() {
        email = UserGenerator.getRandomEmail();
        password = UserGenerator.getRandomPassword(5);
        registrationPage.enterFormRegistration(UserGenerator.getRandomName(), email, password);
        registrationPage.clickButtonRegistrationOnRegistrationPage();
        assertEquals(MESSAGE_ERROR_WRONG_PASSWORD, ERROR_WRONG_PASS_REGISTRATION, registrationPage.getTextErrorWrongPassword());
    }

    @After
    @Step("Удаление созданного пользователя")
    public void tearDown() {
        if (email != null && password != null) {
            LoginUserRequest loginUserRequest = new LoginUserRequest(email, password);
            Response loginResponse = usersSteps.loginUser(loginUserRequest);
            if (loginResponse.statusCode() == SC_OK) {
                accessToken = loginResponse.path("accessToken");
                usersSteps.deleteUser(accessToken);
            }
        }
        driver.quit();
    }
}
