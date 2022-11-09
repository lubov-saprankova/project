package ru.iq_soft;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.*;

public class BacketPage extends AbstractPage{
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(xpath = "//a/span[text()='Trout Master Ridge Sbiro (12g Floating)']")
    private WebElement currentCommod;

    @FindBy(xpath = "//span[@class='basket-item-actions-remove']")
    private WebElement deleteCommod;

    @FindBy(xpath = "//div[@data-entity='basket-total-price']")
    private WebElement currentPrice;

    public BacketPage(WebDriver driver){
        super(driver);
    }

    public String getCommodText()throws InterruptedException{
        js.executeScript("window.scrollTo(0, 400)");
        Thread.sleep(1000);
        return this.currentCommod.getText();
    }

    public BacketPage pressDelCommod() throws InterruptedException{
        this.deleteCommod.click();
        Thread.sleep(2500);
        return this;
    }

    public String getPriceText(){
        return this.currentPrice.getText();
    }







}
