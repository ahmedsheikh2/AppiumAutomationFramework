package ecommerce.com.projects.mobile.pages;

import org.openqa.selenium.By;

import static anhtester.com.keywords.MobileUtils.*;

public class HomePage extends CommonPage {

    public HomePage() {
        super();
    }

    public By inputName = By.id("com.androidsample.generalstore:id/nameField");
    public By radioButtonFemale = By.xpath("//android.widget.RadioButton[@text='Female']");
    public By dropdownCountry = By.id("android:id/text1");
    public By valueCountry;
    public By buttonLetsShop = By.id("com.androidsample.generalstore:id/btnLetsShop");
    public By errorToast = By.xpath("(//android.widget.Toast)[1]");


    public void typeUserName(String username) {
        setText(inputName, username);
    }

    public void selectFemaleGender() {
        clickElement(radioButtonFemale);
    }

    public void selectCountry(String country) {
        clickElement(dropdownCountry);
        valueCountry = By.xpath("//android.widget.TextView[@text='"+ country +"']");
        clickElement(valueCountry);
    }

    public void clickButtonLetsShop() {
        clickElement(buttonLetsShop);
    }
}
