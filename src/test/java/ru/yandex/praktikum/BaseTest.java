package ru.yandex.praktikum;

import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.helpers.BrowserFactory;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = BrowserFactory.getDriver(browser);
        driver.manage().window().setSize(new Dimension(800, 500));
    }

}
