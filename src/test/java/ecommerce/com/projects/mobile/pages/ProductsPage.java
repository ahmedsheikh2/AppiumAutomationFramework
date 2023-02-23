package ecommerce.com.projects.mobile.pages;

import org.openqa.selenium.By;

import static anhtester.com.keywords.MobileUtils.*;

public class ProductsPage extends CommonPage {

    public ProductsPage() {
        super();
    }

    public By buttonCart = By.id("com.androidsample.generalstore:id/appbar_btn_cart");
    public By listProductNames = By.id("com.androidsample.generalstore:id/productName");
    public By listAddToCartButton = By.id("com.androidsample.generalstore:id/productAddCart");

    public void clickCartButton() {
        clickElement(buttonCart);
    }

    public void addItemToCart(String text) {
        scrollIntoViewByText(text);

        int productCount = getWebElements(listProductNames).size();

        for(int i=0; i<productCount; i++)
        {
            if(getElementTextByIndex(listProductNames, i).equalsIgnoreCase(text)){
                clickElementByIndex(listAddToCartButton, i);
            }
        }
    }
}
