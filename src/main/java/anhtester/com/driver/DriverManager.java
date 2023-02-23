package anhtester.com.driver;

import anhtester.com.constants.FrameworkConstants;
import anhtester.com.helpers.Helpers;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private static final ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();

    private DriverManager() {
        super();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver getAndroidDriver() {
        return androidDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void setAndroidDriver(AndroidDriver driver) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(FrameworkConstants.DEVICE_NAME);
        options.getAppWaitActivity();
        String pathtoFile = Helpers.getCurrentDir() + FrameworkConstants.PATH_TO_APP_FILE;
        options.setApp(pathtoFile);

        driver = new AndroidDriver(new URL("http://"+ FrameworkConstants.IP +":"+ FrameworkConstants.PORT), options);
        DriverManager.androidDriver.set(driver);
    }

    public static void quit() {
        DriverManager.getAndroidDriver().quit();
        DriverManager.androidDriver.remove();
    }

}
