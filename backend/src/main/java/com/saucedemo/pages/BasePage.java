package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    protected static WebDriver driver;

    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    protected void set(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    protected void click(WebElement element) {
        element.click();
    }
}
