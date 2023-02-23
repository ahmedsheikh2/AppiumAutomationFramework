package anhtester.com.keywords;

import anhtester.com.constants.FrameworkConstants;
import anhtester.com.driver.DriverManager;
import anhtester.com.helpers.Helpers;
import anhtester.com.report.AllureManager;
import anhtester.com.report.ExtentReportManager;
import anhtester.com.report.ExtentTestManager;
import anhtester.com.utils.DateUtils;
import anhtester.com.utils.Log;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static anhtester.com.constants.FrameworkConstants.*;

public class MobileUtils {

    /**
     * Chuyển đổi đối tượng dạng By sang WebElement
     * Để tìm kiếm một element
     *
     * @param by là element thuộc kiểu By
     * @return Trả về là một đối tượng WebElement
     */
    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getAndroidDriver().findElements(by);
    }

    /**
     * Click Element method for Mobile
     *
     * @param by AppiumBy mobile element
     */
    @Step("Click on the element {0}")
    public static void clickElement(By by) {
        smartWait();
        waitForElementVisible(by).click();

        Log.info("Click on element " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clicked on the object " + by.toString());
        }

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Click on the element {0}")
    public static void clickElementByIndex(By by, int index) {
        smartWait();
        getWebElements(by).get(index).click();

        Log.info("Click on element " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clicked on the object " + by.toString());
        }

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    /**
     * Điền giá trị vào ô Text
     *
     * @param by    element dạng đối tượng By
     * @param value giá trị cần điền vào ô text
     */
    @Step("Set text on textbox")
    public static void setText(By by, String value) {
        waitForElementVisible(by).sendKeys(value);
        Log.info("Set text " + value + " on " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Set text " + value + " on " + by.toString());
        }
        AllureManager.saveTextLog("Set text " + value + " on " + by.toString());

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Log.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element visible {0} with timeout {1} second")
    public static boolean verifyElementVisible(By by, long timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Log.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            Log.error("The element is not visible. " + e.getMessage());
            Assert.fail("The element is NOT visible. " + by);
            return false;
        }
    }

    /**
     * Chờ đợi element sẵn sàng hiển thị để thao tác theo thời gian tuỳ ý
     *
     * @param by      element dạng đối tượng By
     * @param timeOut thời gian chờ tối đa
     * @return một đối tượng WebElement đã sẵn sàng để thao tác
     */
    public static WebElement waitForElementVisible(By by, long timeOut) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));

            boolean check = verifyElementVisible(by, timeOut);
            if (check == true) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElement(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            Log.error("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public static WebElement waitForElementVisible(By by) {
        smartWait();
        waitForElementPresent(by);

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            boolean check = isElementVisible(by, 1);
            if (check == true) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElement(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            Log.error("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    @Step("Verify element visible {0}")
    public static boolean isElementVisible(By by, long timeout) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            Log.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Chờ đợi element sẵn sàng tồn tại trong DOM theo thời gian tuỳ ý
     *
     * @param by element dạng đối tượng By
     * @return một đối tượng WebElement đã tồn tại
     */
    public static WebElement waitForElementPresent(By by) {
        smartWait();

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Log.error("Element not exist. " + by.toString());
            Assert.fail("Element not exist. " + by.toString());
        }
        return null;
    }

    public static void smartWait() {
        if (ACTIVE_PAGE_LOADED.trim().toLowerCase().equals("true")) {
//            waitForPageLoaded();
        }
        sleep(WAIT_SLEEP_STEP);
    }

    /**
     * Hard Wait in Number of seconds
     *
     * @param second
     */
    public static void sleep(double second) {
        try {
            Thread.sleep((long) (second * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void waitForPageLoaded() {
//        WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED), Duration.ofMillis(500));
//        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//
//        // wait for Javascript to loaded
//        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
//
//        //Get JS is Ready
//        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
//
//        //Wait Javascript until it is Ready!
//        if (!jsReady) {
//            Log.info("Javascript in NOT Ready!");
//            //Wait for Javascript to load
//            try {
//                wait.until(jsLoad);
//            } catch (Throwable error) {
//                error.printStackTrace();
//                Assert.fail("Timeout waiting for page load (Javascript). (" + WAIT_PAGE_LOADED + "s)");
//            }
//        }
//    }

    public static void addScreenshotToReport(String screenshotName) {
        if (SCREENSHOT_ALL_STEPS.equals(YES)) {
            if (ExtentTestManager.getExtentTest() != null) {
                ExtentReportManager.addScreenShot(Helpers.makeSlug(screenshotName));
            }
            //CaptureHelpers.captureScreenshot(DriverManager.getDriver(), Helpers.makeSlug(screenshotName));
            AllureManager.takeScreenshotStep();
        }
    }

    @Step("Scroll to element {0}")
    public static void scrollToElement(By by) {
        smartWait();

        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        Log.info("Scroll to element " + by);
    }

    @Step("Verify text of an element [Equals]")
    public static boolean verifyElementTextEquals(By by, String text) {
        smartWait();
        waitForElementVisible(by);

        boolean result = getTextElement(by).trim().equals(text.trim());

        if (result == true) {
            Log.info("Verify text of an element [Equals]: " + result);
        } else {
            Log.warn("Verify text of an element [Equals]: " + result);
        }

        Assert.assertEquals(getTextElement(by).trim(), text.trim(), "The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");

        AllureManager.saveTextLog("Verify text of an element [Equals] : " + result + ". The actual text is '" + getTextElement(by).trim() + "' not equals '" + text.trim() + "'");

        addScreenshotToReport(Thread.currentThread().getStackTrace()[1].getMethodName() + "_" + DateUtils.getCurrentDateTime());

        return result;
    }

    /**
     * Get text of a element
     *
     * @param by element dạng đối tượng By
     * @return text of a element
     */
    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        smartWait();
        AllureManager.saveTextLog("Get text of element " + by.toString());
        AllureManager.saveTextLog("==> The Text is: " + waitForElementVisible(by).getText());
        return waitForElementVisible(by).getText().trim();
    }

    @Step("Get text of element {0}")
    public static String getElementTextByIndex(By by, int index) {
        smartWait();

        Log.info("Click on element " + by.toString());

        if (ExtentTestManager.getExtentTest() != null) {
            ExtentReportManager.pass("Clicked on the object " + by.toString());
        }

        return getWebElements(by).get(index).getText().trim();
    }


    @Step("Verify element {0} with attribute {1} has value is {2}")
    public static boolean verifyElementAttributeValue(By by, String attribute, String value) {
        smartWait();
//        waitForElementVisible(by);
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getAndroidDriver(), Duration.ofSeconds(WAIT_EXPLICIT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.attributeToBe(by, attribute, value));
            return true;
        } catch (Throwable error) {
            Log.error("Object: " + by.toString() + ". Not found value: " + value + " with attribute type: " + attribute + ". Actual get Attribute value is: " + getAttributeElement(by, attribute));
            Assert.fail("Object: " + by.toString() + ". Not found value: " + value + " with attribute type: " + attribute + ". Actual get Attribute value is: " + getAttributeElement(by, attribute));
            return false;
        }
    }

    @Step("Get attribute {1} of element {0}")
    public static String getAttributeElement(By by, String attributeName) {
        smartWait();
        return waitForElementVisible(by).getAttribute(attributeName);
    }

    @Step("Get attribute {1} of element {0}")
    public static void scrollIntoViewByText(String text) {
        smartWait();
        DriverManager.getAndroidDriver().findElement(AppiumBy
                .androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
        smartWait();
    }

}
