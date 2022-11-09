package ru.iq_soft;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

public class CommodPage extends MainPage {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(xpath = "//div[@class='col-12']//a[@href='/gruza/']")
    private WebElement mainMenuItem;

    @FindBy(xpath = "//div[@class='row']//a[@href='/gruza/bombardy/']")
    private WebElement categoryItem;

    @FindBy(xpath = "//a[@href='/gruza/bombardy/trout-master-ridge-sbiro/']")
    private WebElement commodItem;

    @FindBy(xpath = "//td//strong[text()='12g Floating']/../..")
    private WebElement commodVersionItem;

    @FindBy(xpath = "//input[@name='tocart[add]']")
    private WebElement addCommodToBacket;

    @FindBy(xpath = "//li//a[@href='/cabinet/backet/']//i")
    private WebElement backetBtt;

    public CommodPage(WebDriver driver){
        super(driver);
    }

    public CommodPage pressMainMenuItem(){
        this.mainMenuItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/gruza/"));
        return this;
    }

    public CommodPage pressCategoryItem(){
        this.categoryItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/gruza/bombardy/"));
        return this;
    }

    public CommodPage pressCommodItem(){
        this.commodItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/gruza/bombardy/trout-master-ridge-sbiro/"));
        return this;
    }

    public CommodPage pressCommodVersionItem() throws InterruptedException{
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        this.commodVersionItem.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("trout-master-ridge-sbiro/trout-master-ridge-sbiro-12g-floating/"));
        return this;
    }

    public CommodPage pressAddCommodToBacket() throws InterruptedException{
        js.executeScript("window.scrollTo(0,700)");
        Thread.sleep(1000);
        this.addCommodToBacket.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("trout-master-ridge-sbiro/trout-master-ridge-sbiro-12g-floating/"));
        return this;
    }

    public CommodPage pressBacketBtt(){
        getDriver().get("https://ribomaniya.ru/cabinet/basket/");
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/cabinet/basket/"));
        return this;
    }

}
