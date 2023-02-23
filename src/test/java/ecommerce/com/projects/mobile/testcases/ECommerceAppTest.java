package ecommerce.com.projects.mobile.testcases;

import anhtester.com.common.BaseTestMobile;
import anhtester.com.dataprovider.DataProviderManager;
import ecommerce.com.projects.mobile.models.ItemsDataModel;
import ecommerce.com.projects.mobile.pages.CartPage;
import ecommerce.com.projects.mobile.pages.HomePage;
import ecommerce.com.projects.mobile.pages.ProductsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import java.util.Hashtable;
import static anhtester.com.keywords.MobileUtils.*;


@Epic("Test Assignment")
@Feature("E-Commerce Test App Assignment")
public class ECommerceAppTest extends BaseTestMobile {

    public ECommerceAppTest() {
        homePage = new HomePage();
        cartPage = new CartPage();
        productsPage = new ProductsPage();
    }

    @Test(priority = 1, description = "TC01_VerifyErrorMsgForMissingNameField")
    public void verifyMissingNameErrorMsg() {

        homePage.selectFemaleGender();
        homePage.selectCountry("Algeria");
        homePage.clickButtonLetsShop();
        verifyElementAttributeValue(homePage.errorToast, "name", "Please enter your name");
    }

    @Test(priority = 2, description = "TC_02_AddMultipleItemsToCart", dataProvider = "getItemsDataHashTable", dataProviderClass = DataProviderManager.class)
    public void addItemToCart(Hashtable<String, String> data) {

        homePage.typeUserName(data.get(ItemsDataModel.username));
        homePage.selectFemaleGender();
        homePage.selectCountry(data.get(ItemsDataModel.country));
        homePage.clickButtonLetsShop();

        productsPage.addItemToCart(data.get(ItemsDataModel.item));
        productsPage.clickCartButton();

        cartPage.verifyItemIsAddedInCart(data.get(ItemsDataModel.item));

    }

}
