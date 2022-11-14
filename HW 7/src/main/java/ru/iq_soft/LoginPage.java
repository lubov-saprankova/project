package ru.iq_soft;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.*;

public class LoginPage extends MainPage {
    final String errorMessage = "Неверный логин или пароль.";

    @FindBy(xpath = "//input[@name='USER_LOGIN']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='USER_PASSWORD']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='Login']")
    private WebElement inBtt;

    @FindBy(xpath = "//font[@class='errortext']")
    private WebElement error;

    public LoginPage(WebDriver driver){
        super (driver);
    }

    public LoginPage setLogin(String login){
        this.loginField.click();
        this.loginField.clear();
        this.loginField.sendKeys(login);
        return this;
    }

    public LoginPage setPassword(String password){
        this.passwordField.click();
        this.passwordField.clear();
        this.passwordField.sendKeys(password);
        return this;
    }

    public LoginPage pressInBtt(){
        this.inBtt.click();
        return this;
    }

    public Boolean isError(){
        return this.error.getText().equals(errorMessage);
    }

}
