package anhtester.com.common;

import anhtester.com.driver.AppiumManager;
import anhtester.com.driver.DriverManager;
import anhtester.com.listeners.TestListener;
import anhtester.com.projects.website.crm.pages.CommonPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

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
