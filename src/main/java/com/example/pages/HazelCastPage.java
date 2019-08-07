package com.example.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HazelCastPage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[i[@class='icon-signin']]")
    private WebElement signInBtn;

    @FindBy(xpath = "//div[@id='sidebar-menu']//span[starts-with(.,'Maps [')]")
    private WebElement sideBarMaps;

    @FindBy(xpath = "//div[@role='heading']//input")
    private WebElement searchMapField;

    @FindBy(xpath = "//a[i[@class='icon-external-link']]")
    private List<WebElement> maps;

    @FindBy(xpath = "//a[@data-test='menu-console']")
    private WebElement consoleMenu;

    @FindBy(xpath = "//textarea[@id='commandOutput']")
    private WebElement consoleTextArea;

    @FindBy(xpath = "//input[@id='commandInput']")
    private WebElement consoleInput;

    @FindBy(xpath = "//a[@title='Logout']")
    private WebElement logOutBtn;

    @Override
    public String getPageUrl() {
        return null;
    }

    public HazelCastPage open() {
        openPage(this.getClass());
        return this;
    }

    public void login(String userName, String password) {
        type(this.userName, userName);
        type(this.password, password);
        click(this.signInBtn);
        waitUntilLoaded(sideBarMaps);
    }

    public void clickMaps() {
        click(sideBarMaps);
        waitUntilLoaded(searchMapField);
    }

    public void clearSearchMap() {
        clear(searchMapField);
        waitUntilLoaded(maps.get(0));
    }

    public List<String> getAllMaps() {
        return maps.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickConsole() {
        click(consoleMenu);
        waitUntilLoaded(consoleTextArea);
    }

    public void typeConsole(String text) {
        type(consoleInput, text);
        consoleInput.sendKeys(Keys.ENTER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        click(logOutBtn);
        waitUntilLoaded(userName);
    }
}
