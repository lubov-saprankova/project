package ru.iq_soft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class RybomaniaExample {
    public static void main(String[] args) throws InterruptedException {


        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://ribomaniya.ru/?logout=yes");


        driver.get("https://ribomaniya.ru/");
        String s = driver.findElement(By.xpath("//div[@class='text-nowrap pe-2']/a")).getText();
        assert (s.equals("8 (800) 350-32-12"));

        System.out.println();


        Thread.sleep(2000);
        driver.quit();
    }
}
