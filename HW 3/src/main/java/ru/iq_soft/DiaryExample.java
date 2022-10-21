package ru.iq_soft;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DiaryExample {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty(
                "Webdriver.chrome.driver", "src/main/resources/chromedriver.exe"
        );

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.get("https://diary.ru/user/login");



        //driver.findElement(By.id("loginform-username")).sendKeys("LubaSapr");
        //driver.findElement(By.id("loginform-userpassword")).sendKeys("Hh6QufbFhV");
        //Thread.sleep(2000);
       //driver.findElement(By.id("login_btn")).click();


        Cookie authCookie = new Cookie("_identity_", "4b369c07c671d9bdc7d1ee543a609286b337332c9eb3069d4f579be940928371a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A54%3A%22%5B%223573797%22%2C%22BRxijv8Y8MWYzInyfvs5gRKt2WTi9Gp9%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);

        driver.navigate().refresh();


        driver.findElement(By.id("writeThisDiary")).click();

        String postTitle = "title" + new Random().nextInt(100);
        driver.findElement(By.id("postTitle")).sendKeys("postTitle");

        
        driver.switchTo().frame(driver.findElement(By.id("message_ifr")));
        driver.findElement(By.id("tinymce")).sendKeys("text");


        driver.switchTo().parentFrame();
        driver.findElement(By.id("rewrite")).click();

        
        List<WebElement>title = driver.findElements(By.xpath("//a[@class='title=Удалить']"));
        title.stream().filter(p -> p.getText().equals(postTitle)).findFirst().get().click();


        Thread.sleep(5000);
        driver.quit();
    }
}
