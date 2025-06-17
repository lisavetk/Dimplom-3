package ru.yandex.praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.helpers.PathToSelectionConstructor;
import ru.yandex.praktikum.page.objects.ConstructorPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.helpers.PathToSelectionConstructor.*;
import static ru.yandex.praktikum.helpers.TestConstants.*;

@RunWith(Parameterized.class)
@DisplayName("Тесты переходы к разделам в селекторе конструктора")
public class ConstructorTest  extends BaseTest {
    ConstructorPage constructorPage;

    PathToSelectionConstructor pathToSection;
    String description;
    String expectedTitle;

    public ConstructorTest(String description, PathToSelectionConstructor pathToSection, String expectedTitle) {
        this.description = description;
        this.pathToSection = pathToSection;
        this.expectedTitle = expectedTitle;
    }

    @Before
    public void setUp() {
        super.setUp();

        constructorPage = new ConstructorPage(driver);
        constructorPage.openHomePage();
        constructorPage.waitForImages();
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getSection() {
        return new Object[][] {
                {"По умолчанию открыта секция Булки", DEFAULT_BRED, TEXT_SECTION_BREAD},
                {"Нажать в секциях Начинки -> Булки", BREAD_CLICK, TEXT_SECTION_BREAD},
                {"Нажать в секциях Начинки -> скролл до секции Булки", BREAD_SCROLL, TEXT_SECTION_BREAD},
                {"Нажать на Соусы в секциях", SAUCE_CLICK, TEXT_SECTION_SAUCE},
                {"Скролл до секции Соусы", SAUCE_SCROLL, TEXT_SECTION_SAUCE},
                {"Нажать на Начинки в секциях", FILLING_CLICK, TEXT_SECTION_FILLING},
                {"Скролл до секции Начинки", FILLING_SCROLL, TEXT_SECTION_FILLING}
        };
    }


    @Test
    @Description("Осуществляется переход к разделам скроллом или кликом по названию секции в селекторе")
    public void shouldGoToSection() {
        constructorPage.chooseSelection(pathToSection);
        assertEquals(MESSAGE_TEXT_SECTION_NOT_MATCH, expectedTitle, constructorPage.getTextActiveSection());
        assertTrue(MESSAGE_NOT_DISPLAYED_TITLE, constructorPage.chooseTitleSection(pathToSection));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
