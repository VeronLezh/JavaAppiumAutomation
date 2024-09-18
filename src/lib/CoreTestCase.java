package src.lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL="http://127.0.0.1:4723";

@Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "api29");
        capabilities.setCapability("appium:platformVersion", "10.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/veronikalezhneva/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
    }
@Override
    public void tearDown() throws Exception{
        if (driver.getOrientation().equals(ScreenOrientation.LANDSCAPE)) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        driver.quit();
        super.tearDown();
    }
}
