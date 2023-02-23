package anhtester.com.driver;

import anhtester.com.constants.FrameworkConstants;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumManager {

    private AppiumManager() {
        super();
    }

    public static AppiumDriverLocalService appiumService;
    public static AppiumServiceBuilder builder;


    public static void startAppiumService() {
        builder = new AppiumServiceBuilder();
        builder.withIPAddress(FrameworkConstants.IP);
        builder.usingPort(Integer.parseInt(FrameworkConstants.PORT));
        appiumService = AppiumDriverLocalService.buildService(builder);
        appiumService.start();
    }

    public static void stopAppiumService() {
        appiumService.stop();
    }

}
