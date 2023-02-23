package anhtester.com.common;

import anhtester.com.driver.AppiumManager;
import anhtester.com.driver.DriverManager;
import anhtester.com.listeners.TestListener;
import ecommerce.com.projects.mobile.pages.CommonPage;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

@Listeners({TestListener.class})
public class BaseTestMobile extends CommonPage {

    AndroidDriver driver;

    @Parameters("AppiumServer")
    @BeforeSuite(alwaysRun = true)
    public void setup() {
        AppiumManager.startAppiumService();
    }

    @AfterSuite(alwaysRun = true)
    public void closeSession() {
        AppiumManager.stopAppiumService();
    }

    @Parameters("Android")
    @BeforeMethod(alwaysRun = true)
    public void createDriver() throws MalformedURLException {
        DriverManager.setAndroidDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }

}
