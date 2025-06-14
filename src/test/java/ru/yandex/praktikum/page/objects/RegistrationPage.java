package ru.yandex.praktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage extends BasePage {

    private static final By buttonRegistrationsOnAuthPage = By.cssSelector("a[href='/register']");
    private static final By fieldName = By.xpath("//div[label[text()='Имя']]/input");
    private static final By fieldEmail = By.xpath("//div[label[text()='Email']]/input");
    private static final By fieldPassword = By.xpath("//div[label[text()='Пароль']]/input");
    private static final By buttonRegistrationOnRegistrationPage = By.className("button_button__33qZ0");
    private static final By loginTitle = By.xpath("//h2[text()='Вход']");
    private static final By errorWrongPassword = By.className("input__error");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать на кнопку 'Зарегистрироваться' на странице авторизации")
    public void clickButtonRegistrationOnAuthPage() {
        click(buttonRegistrationsOnAuthPage);
    }

    @Step("Ввести значения в поля Имя, Email, пароль на странице регистрации")
    public void enterFormRegistration(String name, String email, String password) {
        enter(fieldName, name);
        enter(fieldEmail, email);
        enter(fieldPassword, password);
    }

    @Step("Нажать на кнопку 'Зарегистрироваться' на странице регистрации")
    public void clickButtonRegistrationOnRegistrationPage() {
        click(buttonRegistrationOnRegistrationPage);
    }

    @Step("Получить текст заголовка формы авторизации")
    public String getTextLoginTitle() {
       return getText(loginTitle);
    }

    @Step("Получить текст ошибки под полем Пароль")
    public String getTextErrorWrongPassword() {
        return getText(errorWrongPassword);
    }

    @Step("Перейти на экран регистрации")
    public void goToRegistrationPage() {
        openHomePage();
        clickButtonPersonalAccount();
        clickButtonRegistrationOnAuthPage();
    }

}
