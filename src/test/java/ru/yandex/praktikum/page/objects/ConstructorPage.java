package ru.yandex.praktikum.page.objects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.praktikum.helpers.PathToSelectionConstructor;

public class ConstructorPage extends MainPage {

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    private static final By buttonBread = By.xpath("//div[span[text()='Булки']]");
    private static final By buttonSauce = By.xpath("//div[span[text()='Соусы']]");
    private static final By buttonFilling = By.xpath("//div[span[text()='Начинки']]");

    private static final By titleBread = By.xpath("//h2[text()='Булки']");
    private static final By titleSauce = By.xpath("//h2[text()='Соусы']");
    private static final By titleFilling = By.xpath("//h2[text()='Начинки']");

    private static final By activeSection = By.xpath("//div[contains(@class,'tab_tab_type_current')]/span");

    @Step("Нажать кнопку Булки в селекторе")
    public void clickButtonBread() {
        click(buttonBread);
    }

    @Step("Скролл до секции Булки")
    public void scrollToTitleBread() {
        WebElement element = driver.findElement(titleBread);
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollBy(0, arguments[0].getBoundingClientRect().top - 100);", element);
    }

    @Step("Проверить видимости заголовка секции Булки")
    public boolean isDisplayedTitleBread() {
        return isDisplayedElement(titleBread);
    }

    @Step("Нажать кнопку Соусы в селекторе")
    public void clickButtonSauce() {
        click(buttonSauce);
    }

    @Step("Скролл до секции Соусы")
    public void scrollToTitleSauce() {
        scrollToElement(titleSauce);
    }

    @Step("Проверить видимости заголовка секции Соусы")
    public boolean isDisplayedTitleSauce() {
        return isDisplayedElement(titleSauce);
    }

    @Step("Нажать кнопку Начинки в селекторе")
    public void clickButtonFilling() {
        click(buttonFilling);
    }

    @Step("Скролл до секции Начинки")
    public void scrollToTitleFilling() {
        scrollToElement(titleFilling);
    }

    @Step("Проверить видимости заголовка секции Начинки")
    public boolean isDisplayedTitleFilling() {
        return isDisplayedElement(titleFilling);
    }

    @Step("Подождать загрузки всех изображений")
    public void waitForImages() {
        waitForImagesToLoad();
    }

    @Step("Получить текст активной секции")
    public String getTextActiveSection() {
       return getText(activeSection);
    }

    public void chooseSelection(PathToSelectionConstructor path) {
        switch (path) {
            case BREAD_CLICK:
                clickButtonFilling();
                clickButtonBread();
                break;
            case BREAD_SCROLL:
                clickButtonFilling();
                scrollToTitleBread();
                break;
            case SAUCE_CLICK:
                clickButtonSauce();
                break;
            case SAUCE_SCROLL:
                scrollToTitleSauce();
                break;
            case FILLING_CLICK:
                clickButtonFilling();
                break;
            case FILLING_SCROLL:
                scrollToTitleFilling();
                break;
            case DEFAULT_BRED:
                break;
        }
    }

    public boolean chooseTitleSection(PathToSelectionConstructor path) {
        switch (path) {
            case BREAD_CLICK:
                return isDisplayedTitleBread();
            case BREAD_SCROLL:
                return isDisplayedTitleBread();
            case SAUCE_CLICK:
                return isDisplayedTitleSauce();
            case SAUCE_SCROLL:
                return isDisplayedTitleSauce();
            case FILLING_CLICK:
                return isDisplayedTitleFilling();
            case FILLING_SCROLL:
                return  isDisplayedTitleFilling();
            case DEFAULT_BRED:
                return isDisplayedTitleBread();
            default:
                return false;
        }
    }
}
