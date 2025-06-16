package ru.yandex.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        //для гугла
        /*WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();*/

        //для яндекса
        WebDriverManager.chromedriver().driverVersion("134.0.6998.0").setup();
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(800, 400));

    }

}
