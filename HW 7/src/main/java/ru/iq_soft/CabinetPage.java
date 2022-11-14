package ru.iq_soft;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;

public class CabinetPage extends AbstractPage{
    JavascriptExecutor js = (JavascriptExecutor) getDriver();

    @FindBy(xpath = "//a[@href='/cabinet/personal/']")
    private WebElement personalBtt;

    @FindBy(xpath = "//input[@name='NAME']")
    private WebElement nameInputField;

    @FindBy(xpath = "//input[@name='SECOND_NAME']")
    private WebElement secondNameInputField;

    @FindBy(xpath = "//input[@name='LAST_NAME']")
    private WebElement lastNameInputField;

    @FindBy(xpath = "//input[@name='NEW_PASSWORD']")
    private WebElement newPasswordInputField;

    @FindBy(xpath = "//input[@name='save']")
    private WebElement applyBtt;

    public CabinetPage (WebDriver driver){
        super(driver);
    }

    public CabinetPage pressPersonlBtt(){
        this.personalBtt.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/cabinet/personal"));
        return this;
    }

    public CabinetPage pressApplyBtt()  throws InterruptedException{
        // new Actions(getDriver()).moveToElement(this.applyBtt).click().perform();
        //  не проходит (в данном случае) и нужно делать скролл…
        js.executeScript("window.scrollTo(0, 900)");
        Thread.sleep(1000);
        this.applyBtt.click();
        new WebDriverWait(getDriver(), 10).until(ExpectedConditions.urlContains("/cabinet/personal"));
        return this;
    }

    public CabinetPage setNameField(String newName){
        this.nameInputField.click();
        this.nameInputField.clear();
        this.nameInputField.sendKeys(newName);
        return this;
    }

    public CabinetPage setSecondNameField(String newValue){
        this.secondNameInputField.click();
        this.secondNameInputField.clear();
        this.secondNameInputField.sendKeys(newValue);
        return this;
    }

    public CabinetPage setLastNameField(String newValue){
        this.lastNameInputField.click();
        this.lastNameInputField.clear();
        this.lastNameInputField.sendKeys(newValue);
        return this;
    }

    public CabinetPage clearNewPasswordField(){
        this.newPasswordInputField.clear();
        return this;
    }

}
