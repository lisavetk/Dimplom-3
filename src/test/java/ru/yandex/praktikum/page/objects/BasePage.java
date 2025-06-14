package ru.yandex.praktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.helpers.EnvConfig.*;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    private static final By buttonPersonalAccount = By.xpath("//a[p[text()='Личный Кабинет']]");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void enter(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    protected String getText(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    @Step("Открыть домашнюю страницу")
    public void openHomePage() {
        open(BASE_URL);
    }

    @Step("Нажать на кнопку 'Личный кабинет'")
    public void clickButtonPersonalAccount() {
        click(buttonPersonalAccount);
    }

}
