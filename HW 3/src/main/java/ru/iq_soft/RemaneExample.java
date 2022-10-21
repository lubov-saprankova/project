
package ru.iq_soft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class RemaneExample {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://ribomaniya.ru/?logout=yes");

        driver.findElement(By.xpath("//div[@class='col-12']//a[@href='/gruza/']")).click();
        driver.findElement(By.xpath("//div[@class='row']//a[@href='/gruza/bombardy/']")).click();
        driver.findElement(By.xpath("//a[@href='/gruza/bombardy/trout-master-ridge-sbiro/']")).click();

        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//td//strong[text()='12g Floating']/../..")).click();

        js.executeScript("window.scrollTo(0, 700)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@name='tocart[add]']")).click();
        driver.get("https://ribomaniya.ru/cabinet/basket/");
        js.executeScript("window.scrollTo(0, 500)");
        Thread.sleep(1000);
            // снова вертикальный скролл…
        String s = driver.findElement(By.xpath("//a[@href='/gruza/bombardy/trout-master-ridge-sbiro/trout-master-ridge-sbiro-12g-floating/']")).getText();
        assert (s.equals("Trout Master Ridge Sbiro (12g Floating)"));

        driver.findElement(By.xpath("//span[@class='basket-item-actions-remove']")).click();

        String s1 = driver.findElement(By.xpath("//div[@data-entity='basket-total-price']")).getText();

        assert (s1.equals("0 р."));



        System.out.println();

        Thread.sleep(5000);
        driver.quit();
    }
}
