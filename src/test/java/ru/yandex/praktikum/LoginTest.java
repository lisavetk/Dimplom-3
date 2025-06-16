package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.yandex.praktikum.api.CreateUserRequest;
import ru.yandex.praktikum.api.UsersSteps;
import ru.yandex.praktikum.helpers.PathToLogin;
import ru.yandex.praktikum.helpers.UserGenerator;
import ru.yandex.praktikum.page.objects.LoginPage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.helpers.PathToLogin.*;
import static ru.yandex.praktikum.helpers.TestConstants.*;

@DisplayName("Тесты на авторизацию пользователя")
@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    String email =  UserGenerator.getRandomEmail();
    String password = UserGenerator.getRandomPassword(6);
    LoginPage loginPage;

    PathToLogin pathToLogin;
    String description;

    UsersSteps usersSteps = new UsersSteps();
    String accessToken;

    public LoginTest(String description, PathToLogin pathToLogin) {
        this.description = description;
        this.pathToLogin = pathToLogin;
    }

    @Before
    @DisplayName("Регистрация нового пользователя с корректными данными имени, почты и пароля")
    @Description("Для регистрации используются рандомные данные, генерируемые случайным образом. Данные сохраняются для использования в тестах на авторизацию")
    public void setUp() {
        //для гугла
        /*WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();*/

        //для яндекса
        WebDriverManager.chromedriver().driverVersion("134.0.6998.0").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);

        CreateUserRequest createUserRequest = new CreateUserRequest(email, password, RandomStringUtils.randomAlphabetic(10));
        accessToken =  usersSteps.createUser(createUserRequest).path("accessToken");

        loginPage = new LoginPage(driver);
        loginPage.openHomePage();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getButton() {
        return new Object[][] {
                {"С главной страницы - кнопка Войти в аккаунт", MAIN_PAGE},
                {"Из шапки - кнопка Личный кабинет", HEADER},
                {"Со страницы регистрации - кнопка Войти", REGISTRATION_PAGE},
                {"Со страницы восстановления пароля - кнопка Войти", RECOVERY_PASSWORD_PAGE}
        };
    }

    @Test
    @Description("Вводятся корректные данные почты и пароля ранее зарегистрированного пользователя.")
    public void shouldLoginUser() {
        loginPage.chooseButtonLogin(pathToLogin);
        loginPage.enterFormLogin(email, password);
        loginPage.clickButtonLogin();
        assertEquals(MESSAGE_TEXT_BUTTON_CREATE_ORDER_AFTER_LOGIN, TEXT_BUTTON_CREATE_ORDER, loginPage.getTextButtonCreateOrder());
    }

    @After
    @DisplayName("Выход из аккаунта пользователя")
    public void tearDown() {
        if (accessToken != null) {
            usersSteps.deleteUser(accessToken);
        }
        driver.quit();
    }
}
