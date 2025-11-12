package com.saucedemo.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class ProductsPage extends BasePage {
    @FindBy(xpath = "//span[text()='Products']") private WebElement productsHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(BasePage.driver, this);

    }
    public boolean isProductsHeaderDisplayed() {
        return this.productsHeader.isDisplayed();
    }
}
