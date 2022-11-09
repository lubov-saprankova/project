
package ru.iq_soft;

import java.util.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

public class MainPageTest extends AbstractTest{

    @Test
    @DisplayName("Тест-кейс №3: Проверка правильности отображения номера телефона на главной странице")
    public void testCase3() {
        getWebDriver().get("https://ribomaniya.ru");
        assertTrue(
                new MainPage(getWebDriver()).getPhoneNum().equals("8 (800) 350-32-12")
        );
        logger.info("Тест-кейс №3 пройден");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/menudata.csv")
    @DisplayName("Тест-кейс №5: Проверка работы главных полей товарного каталога сайта")
    public void testCase5(String s_xpath, String s_title, String s_header) throws InterruptedException {
        getWebDriver().get("https://ribomaniya.ru");
        MainPage e = new MainPage(getWebDriver());
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title));
        e.pressMenuItem(s_xpath);
        assertTrue(e.checkMenuItemTitle(s_xpath,s_title));
        assertTrue(e.checkContentHeader(s_header));
        logger.info("Тест-кейс №5 пройден ("+s_title+")");
    }

}
