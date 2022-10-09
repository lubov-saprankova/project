package com.example.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ProfilePage {

    public WebDriver driver;
    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[contains(@class, 'account__name_hasAccentLetter')]")
    private WebElement userMenu;

    @FindBy(xpath = "//*[contains(@class, 'menu-item_action_exit menu__item menu__item_type_link')]")
    private WebElement logoutBtn;

    public String getUserName() {
        String userName = userMenu.getText();
        return userName; }

    public void entryMenu() {
        userMenu.click(); }

    public void userLogout() {
        logoutBtn.click(); } }

