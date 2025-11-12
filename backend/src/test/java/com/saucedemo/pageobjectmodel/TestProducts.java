package com.saucedemo.pageobjectmodel;

import com.saucedemo.pages.ProductsPage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TestProducts extends BaseTest {
    @Test
    public void testLoginSuccess() {
        ProductsPage productsPage = this.loginPage.logIntoApplication("standard_user","secret_sauce");
        assertTrue(productsPage.isProductsHeaderDisplayed());

    }
}
