package ru.yandex.praktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.yandex.praktikum.helpers.EnvConfig.BASE_URL;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private static final By buttonPersonalAccount = By.xpath("//a[p[text()='Личный Кабинет']]");
    protected static final By buttonCreateOrder = By.xpath("//button[text()='Оформить заказ']");
    private static final By buttonLogout = By.xpath("//button[text()='Выход']");

    private static final By buttonRegistrationsOnAuthPage = By.cssSelector("a[href='/register']");

    @Step("Открыть домашнюю страницу")
    public void openHomePage() {
        open(BASE_URL);
    }

    @Step("Нажать на кнопку 'Личный кабинет'")
    public void clickButtonPersonalAccount() {
        click(buttonPersonalAccount);
    }

    @Step("Получить текст кнопки Оформить заказ")
    public String getTextButtonCreateOrder() {
        return getText(buttonCreateOrder);
    }

    @Step("Выйти из аккаунта пользователя")
    public void logout() {
        clickButtonPersonalAccount();
        click(buttonLogout);
        openHomePage();
    }

    @Step("Нажать на кнопку 'Зарегистрироваться' на странице авторизации")
    public void clickButtonRegistrationOnAuthPage() {
        click(buttonRegistrationsOnAuthPage);
    }
}
