package ru.iq_soft;
/**
 * Класс AbstractTest
 *       Суперкласс тестов
 * @author : Сапранькова Л.Л.
 * @project : HW_6
 * @date : 14.11.2022
 */

import java.util.*;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractTest {

    // static WebDriver driver; — он оборачивается, поэтому как отдельная переменная существовать он будет
    Logger logger = LoggerFactory.getLogger("Test-Case's 1-6");
    static EventFiringWebDriver driver;

    @BeforeAll
    static void initClass() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        /*
        Столкнулась с проблемой, при которой первый тест-кейс в наборе вылетал по таймауту (без перехода к самому тесту,
        но после выполнения блока BeforeEach), а остальные работали нормально.
         */

        // на всякий случай оставляю все возможные опции, которые могут избавить от этой проблемы
        //options.addArguments("enable-automation");                // https://stackoverflow.com/a/43840128/1689770
        //options.addArguments("--headless");                       // only if you are ACTUALLY running headless
        options.addArguments("--no-sandbox");                       //https://stackoverflow.com/a/50725918/1689770
        options.addArguments("--disable-infobars");                 //https://stackoverflow.com/a/43840128/1689770
        //options.addArguments("--disable-dev-shm-usage");          //https://stackoverflow.com/a/50725918/1689770
        //options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
        options.addArguments("--disable-gpu");

        /*
        driver = new ChromeDriver(options);
            вместо этого драйвер оборачивается в EventFiringWebDriver и в нем регистрируется MyWebDriverEventListener
            в котором прописываются точки логирования на любое из действий
        */

        driver = new EventFiringWebDriver(new ChromeDriver(options));
        driver.register(new MyWebDriverEventListener());

        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
    }

    @BeforeEach
    void initTest() {
        driver.get("https://ribomaniya.ru/?logout=yes");
    }

    @AfterAll
    static void finalClass() {
        if(driver !=null) driver.quit();
    }

    public WebDriver getWebDriver(){
        return this.driver;
    }

}
