package ecommerce.com.projects.mobile.pages;

import org.openqa.selenium.By;

import static anhtester.com.keywords.MobileUtils.verifyElementTextEquals;

public class CartPage {

    public By labelCartItem = By.id("com.androidsample.generalstore:id/productName");

    public void verifyItemIsAddedInCart(String itemName) {
        verifyElementTextEquals(labelCartItem, itemName);
    }
}
