package com.saucedemo.pageobjectmodel;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestLogin extends BaseTest {

    @Test
    public void testLoginErrorMessage() {
        this.loginPage.logIntoApplication("standard_user","incorrect_password");
        String actual = this.loginPage.getErrorMessage();
        String expected = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(actual, expected);
    }
}
