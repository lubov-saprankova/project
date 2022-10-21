package ru.iq_soft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AfishaExample {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty(
                "Webdriver.chrome.driver", "src/main/resources/chromedriver.exe"
        );

        WebDriverManager.chromedriver().setup();


        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.afisha.ru/");

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        WebElement inputSearch = webDriver.findElement(By.xpath("//input[@placeholder='Событие, актер, место']"));
        inputSearch.sendKeys("Брат");
        Thread.sleep(5000);

        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        //webDriverWait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[.='Брат']"))));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='Брат']")));


        webDriver.findElement(By.xpath("//div[.='Брат']")).click();

        Thread.sleep(5000);
        webDriver.quit();



    }
}
