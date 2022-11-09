package ru.iq_soft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends AbstractTest{

    @Test
    @DisplayName("Тест-кейс №1: Авторизация на сайте")
    public void testCase1() {
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        new LoginPage(getWebDriver())
                .setLogin("SapraL22")
                .setPassword("211022Sa")
                .pressInBtt();
        assertTrue(new MainPage(getWebDriver()).checkUser("Любовь"));
        logger.info("Тест-кейс №1 пройден");
    }

    @Test
    @DisplayName("Тест-кейс №2: Авторизация на сайте (негативный тест)")
    public void testCase2() {
        getWebDriver().get("https://ribomaniya.ru");
        new MainPage(getWebDriver()).pressLoginBtt();
        assertTrue(
                new LoginPage(getWebDriver())
                        .setLogin("MerlinStoun")
                        .setPassword("password")
                        .pressInBtt()
                        .isError()
        );
        logger.info("Тест-кейс №2 пройден");
    }

}
