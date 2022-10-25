
package ru.iq_soft;

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class HW5Test {

    static WebDriver driver;
    Logger logger = LoggerFactory.getLogger("Test-Case's 1-4");
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeAll
    static void initClass() {
        System.setProperty(
                "Webdriver.chrome.driver", "src/main/resources/chromedriver.exe"
        );

        WebDriverManager.chromedriver().setup();


        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

    }

    @BeforeEach
    void initTest() {
        driver.get("https://ribomaniya.ru/?logout=yes");
    }

    @AfterAll
    static void finalClass() {
        driver.quit();
    }

    @Test
    @DisplayName("Тест-кейс №1: Авторизация сайте")
    public void testCase1() {

        driver.get("https://ribomaniya.ru/cabinet/auth/?login=yes&backurl=%2F");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).clear();
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).clear();
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("SapraL22");
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys("211022Sa");
        driver.findElement(By.xpath("//button[@name='Login']")).click();
        String s = driver.findElement(By.xpath("//a[@class='nav-link text-truncate pt-0']")).getText();
        assertTrue(s.equals("Любовь"));

        logger.info("Тест-кейс №1 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №2: Авторизация на сайте (негативный сценарий)")
    public void testCase2() {
        driver.get("https://ribomaniya.ru/cabinet/auth/?login=yes&backurl=%2F");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).clear();
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).clear();
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("sapra22");
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys("password");
        driver.findElement(By.xpath("//button[@name='Login']")).click();
        String s = driver.findElement(By.xpath("//font[@class='errortext']")).getText();
        assertTrue(s.equals("Неверный логин или пароль."));


        logger.info("Тест-кейс №2 пройден");

    }

    @Test
    @DisplayName("Тест-кейс №3: Проверка правильности отображения номера телефона на главной странице ")
    public void testCase3() {
        driver.get("https://ribomaniya.ru/");
        String s = driver.findElement(By.xpath("//div[@class='text-nowrap pe-2']/a")).getText();
        assertTrue(s.equals("8 (800) 350-32-12"));

        logger.info("Тест-кейс №3 пройден");

    }

    @Test
    @DisplayName("Тест-кейс №4: Выбор товара и добавление его в корзину, удаление его из корзины как позиции")
    public void testCase4() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='col-12']//a[@href='/gruza/']")).click();
        driver.findElement(By.xpath("//div[@class='row']//a[@href='/gruza/bombardy/']")).click();
        driver.findElement(By.xpath("//a[@href='/gruza/bombardy/trout-master-ridge-sbiro/']")).click();
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//td//strong[text()='12g Floating']/../..")).click();
        js.executeScript("window.scrollTo(0, 700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='tocart[add]']")).click();
        driver.get("https://ribomaniya.ru/cabinet/basket/");
        js.executeScript("window.scrollTo(0, 400)");
        Thread.sleep(1000);
        String s = driver.findElement(By.xpath("//a/span[text()='Trout Master Ridge Sbiro (12g Floating)']")).getText();
        assertTrue (s.equals("Trout Master Ridge Sbiro (12g Floating)"));
        driver.findElement(By.xpath("//span[@class='basket-item-actions-remove']")).click();
        Thread.sleep(2500);
        String s1 = driver.findElement(By.xpath("//div[@data-entity='basket-total-price']")).getText();
        assertTrue (s1.equals("0 р."));


        logger.info("Тест-кейс №4 пройден");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menudata.csv")
    @DisplayName("Тест-кейс №5: Проверка работы главных полей товарного каталога сайта")
    public void testCase5(String s_xpath, String s_title, String s_header) {
        WebElement e = driver.findElement(By.xpath(s_xpath));
        String s = e.getText();
        e.click();
        String s1 = driver.findElement(By.xpath("//h1")).getText();
        logger.debug("Пункт меню — "+s);
        logger.debug("Заголовок целевой страницы — "+s1);
        assertTrue(s.equals(s_title));
        assertTrue(s1.equals(s_header));


        logger.info("Тест-кейс №5 пройден ("+s_title+")");
    }

    @Test
    @DisplayName("Тест-кейс №6: Проверка работы формы ввода личных данных")
    public void testCase6() throws InterruptedException {
        driver.get("https://ribomaniya.ru/cabinet/auth/?login=yes&backurl=%2F");
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).clear();
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).clear();
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys("SapraL22");
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys("211022Sa");
        driver.findElement(By.xpath("//button[@name='Login']")).click();
        String s = driver.findElement(By.xpath("//a[@class='nav-link text-truncate pt-0']")).getText();
        assertTrue(s.equals("Любовь"));
        driver.findElement(By.xpath("//a[@href='/cabinet/']//i")).click();
        driver.findElement(By.xpath("//a[@href='/cabinet/personal/']")).click();
        driver.findElement(By.xpath("//input[@name='NAME']")).clear();
        driver.findElement(By.xpath("//input[@name='NAME']")).sendKeys("Иван");
        driver.findElement(By.xpath("//input[@name='SECOND_NAME']")).clear();
        driver.findElement(By.xpath("//input[@name='SECOND_NAME']")).sendKeys("Иванович");
        driver.findElement(By.xpath("//input[@name='LAST_NAME']")).clear();
        driver.findElement(By.xpath("//input[@name='LAST_NAME']")).sendKeys("Иванов");
        driver.findElement(By.xpath("//input[@name='NEW_PASSWORD']")).clear();
        js.executeScript("window.scrollTo(0, 700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='save']")).click();
        driver.get("https://ribomaniya.ru/cabinet/personal/?");

        s = driver.findElement(By.xpath("//a[@class='nav-link text-truncate pt-0']")).getText();
        assertTrue(s.equals("Иван"));


        driver.findElement(By.xpath("//input[@name='NAME']")).clear();
        driver.findElement(By.xpath("//input[@name='NAME']")).sendKeys("Любовь");
        driver.findElement(By.xpath("//input[@name='SECOND_NAME']")).clear();
        driver.findElement(By.xpath("//input[@name='SECOND_NAME']")).sendKeys("Леонидовна");
        driver.findElement(By.xpath("//input[@name='LAST_NAME']")).clear();
        driver.findElement(By.xpath("//input[@name='LAST_NAME']")).sendKeys("0000000");
        driver.findElement(By.xpath("//input[@name='NEW_PASSWORD']")).clear();
        js.executeScript("window.scrollTo(0, 700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='save']")).click();
        driver.get("https://ribomaniya.ru/cabinet/personal/?");
        s = driver.findElement(By.xpath("//a[@class='nav-link text-truncate pt-0']")).getText();
        assertTrue(s.equals("Любовь"));
        logger.info("Тест-кейс №6 пройден");

    }
}
