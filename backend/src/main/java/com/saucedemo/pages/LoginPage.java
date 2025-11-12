package com.saucedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name") private WebElement usernameField;
    @FindBy(id = "password") private WebElement passwordField;
    @FindBy(id = "login-button") private WebElement loginButton;
    @FindBy(css = "#login_button_container h3") private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(BasePage.driver, this);
    }

    // Setter methods
    public void setUsername(String username) {
        this.set(this.usernameField, username);
    }

    public void setPassword(String password) {
        this.set(this.passwordField, password);
    }

    // Transition method
    public ProductsPage clickLoginButton() {
        this.click(this.loginButton);
        return new ProductsPage(driver);
    }

    // Convenience method
    public ProductsPage logIntoApplication(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        return this.clickLoginButton();
    }

    // Getter method
    public String getErrorMessage() {
        return this.errorMessage.getText();
    }
}
