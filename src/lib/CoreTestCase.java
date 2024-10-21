package src.lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.WelcomePageObject;
import src.lib.ui.factories.WelcomePageObjectFactory;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;


@Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        //this.skipWelcome();
        this.openWikiPageForMobileWeb();
    }
@Override
    protected void tearDown() throws Exception{
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            if (driver.getOrientation().equals(ScreenOrientation.LANDSCAPE)) {
                driver.rotate(ScreenOrientation.PORTRAIT);
        }

        }
        driver.quit();
        super.tearDown();
    }
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp does nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }

    public void skipWelcome(){
        if (Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
            WelcomePageObject.clickSkip();
        }
    }

    protected void openWikiPageForMobileWeb(){
    if (Platform.getInstance().isMW()){
        driver.get("https://en.m.wikipedia.org");
    } else {
        System.out.println("Method openWikiPageForMobileWeb does nothing for platform "+Platform.getInstance().getPlatformVar());
    }
    }
}
