package ru.yandex.praktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.praktikum.helpers.PathToLogin;

public class LoginPage extends BasePage {

    private static final By buttonLoginOnMainPage = By.className("button_button__33qZ0");
    private static final By buttonLoginOnRegistrationPage = By.className("Auth_link__1fOlj");
    private static final By buttonRecoverPassword = By.className("Auth_link__1fOlj");
    private static final By buttonLoginOnRecoverPasswordPage = By.className("Auth_link__1fOlj");

    private static final By fieldEmail = By.xpath("//div[label[text()='Email']]/input");
    private static final By fieldPassword = By.xpath("//div[label[text()='Пароль']]/input");

    private static final By buttonLogin = By.className("button_button__33qZ0");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @Step("Нажать кнопку Войти в аккаунт на главной странице")
    public void clickButtonLoginOnMainPage() {
        click(buttonLoginOnMainPage);
    }

    @Step("Нажать кнопку Войти на странице регистрации")
    public void clickButtonLoginOnRegistrationPage() {
        click(buttonLoginOnRegistrationPage);
    }

    @Step("Нажать кнопку Восстановить пароль на странице авторизации")
    public void clickButtonRecoverPassword() {
        click(buttonRecoverPassword);
    }

    @Step("Нажать кнопку Войти на странице восстановления пароля")
    public void clickButtonLoginOnRecoverPasswordPage() {
        click(buttonLoginOnRecoverPasswordPage);
    }

    @Step("Ввести значения в поля Email и пароль на странице авторизации")
    public void enterFormLogin(String email, String password) {
        enter(fieldEmail, email);
        enter(fieldPassword, password);
    }

    @Step("Нажать кнопку Войти на экране авторизации")
    public void clickButtonLogin() {
        click(buttonLogin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCreateOrder));
    }

    @Step("Перейти на экран авторизации с экрана регистрации")
    public void goToLoginPageFromRegistrationPage() {
        clickButtonPersonalAccount();
        clickButtonRegistrationOnAuthPage();
        clickButtonLoginOnRegistrationPage();
    }

    @Step("Перейти на экран авторизации с экрана восстановления пароля")
    public void goToLoginPageFromRecoveryPage() {
        clickButtonPersonalAccount();
        clickButtonRecoverPassword();
        clickButtonLoginOnRecoverPasswordPage();
    }

    public void chooseButtonLogin(PathToLogin path) {
        switch (path) {
            case MAIN_PAGE:
                clickButtonLoginOnMainPage();
                break;
            case HEADER:
                clickButtonPersonalAccount();
                break;
            case REGISTRATION_PAGE:
                goToLoginPageFromRegistrationPage();
                break;
            case RECOVERY_PASSWORD_PAGE:
                goToLoginPageFromRecoveryPage();
            default:
                clickButtonLoginOnMainPage();
        }
    }

}
