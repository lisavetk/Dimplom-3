package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.praktikum.page.objects.RegistrationPage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.TestConstants.*;

@DisplayName("Тесты на регистрацию пользователя")
public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @Before
    public void createDriver() {
        //для гугла
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //для яндекса
        /*WebDriverManager.chromedriver().driverVersion("134.0.6998.0").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);*/

        registrationPage = new RegistrationPage(driver);
        registrationPage.goToRegistrationPage();
    }

    @DisplayName("Тест на успешную регистрацию пользователя")
    @Description("Вводятся корректные данные имени, почты и пароля")
    @Test
    public void shouldRegisterSuccessfullyWithValidData() {
        registrationPage.enterFormRegistration(UserGenerator.getRandomName(), UserGenerator.getRandomEmail(), UserGenerator.getRandomPassword(6));
        registrationPage.clickButtonRegistrationOnRegistrationPage();
        assertEquals(MESSAGE_TITLE_LOGIN_AFTER_REGISTRATION, TITLE_AUTH_PAGE, registrationPage.getTextLoginTitle());

    }

    @DisplayName("Тест на непрошедшую регистрацию пользователя")
    @Description("Вводятся корректные данные имени, почты. Пароль указывается 3 символа")
    @Test
    public void shouldShowErrorWhenPasswordIsTooShort() {
        registrationPage.enterFormRegistration(UserGenerator.getRandomName(), UserGenerator.getRandomEmail(), UserGenerator.getRandomPassword(3));
        registrationPage.clickButtonRegistrationOnRegistrationPage();
        assertEquals(MESSAGE_ERROR_WRONG_PASSWORD, ERROR_WRONG_PASS_REGISTRATION, registrationPage.getTextErrorWrongPassword());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
