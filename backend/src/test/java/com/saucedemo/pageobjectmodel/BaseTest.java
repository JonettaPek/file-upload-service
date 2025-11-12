package com.saucedemo.pageobjectmodel;

import com.saucedemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    private final static String url = "https://www.saucedemo.com";
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get(url);
        this.loginPage = new LoginPage(this.driver);
    }

    @AfterClass
    public void tearDown() {
        this.driver.quit();
    }
}
