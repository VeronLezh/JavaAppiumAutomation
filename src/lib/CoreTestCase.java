package src.lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.WelcomePageObject;
import src.lib.ui.factories.WelcomePageObjectFactory;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;


@Before
@Step("Run driver and session")
    public void setUp() throws Exception {

        driver = src.lib.Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        //this.skipWelcome();
        this.openWikiPageForMobileWeb();
    }
@After
@Step("Remove driver and session")
    public void tearDown(){
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            if (driver.getOrientation().equals(ScreenOrientation.LANDSCAPE)) {
                driver.rotate(ScreenOrientation.PORTRAIT);
        }

        }
        driver.quit();
    }
    @Step("Rotate screen to landscape mode  (Method doesn't work for Mobile web)")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenLandscape does nothing for platform "+src.lib.Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Rotate screen to portrait mode (Method doesn't work for Mobile web)")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortrait does nothing for platform "+src.lib.Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Device to background (Method doesn't work for Mobile web)")
    protected void backgroundApp(int seconds){
        if (driver instanceof AppiumDriver)  {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method backgroundApp does nothing for platform "+src.lib.Platform.getInstance().getPlatformVar());
        }
    }
    @Step("Skip Welcome screen on iOS")
    public void skipWelcome(){
        if (src.lib.Platform.getInstance().isIOS()){
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
            WelcomePageObject.clickSkip();
        }
    }
    @Step("Open Wiki URL on mobile web platform (Method doesn't work on Android and iOS)")
    protected void openWikiPageForMobileWeb(){
    if (src.lib.Platform.getInstance().isMW()){
        driver.get("https://en.m.wikipedia.org");
    } else {
        System.out.println("Method openWikiPageForMobileWeb does nothing for platform "+src.lib.Platform.getInstance().getPlatformVar());
    }
    }

    private void createAllurePropertyFile(){
        String path=System.getProperty("allure.results.directory");
    try{
        Properties props= new Properties();
        FileOutputStream fos= new FileOutputStream(path+"/environment.properties");
        props.setProperty("Environment", src.lib.Platform.getInstance().getPlatformVar());
        props.store(fos,"See https://gihub.com/allure-framework/allure-app/wiki/Environment");
        fos.close();
    } catch (Exception e){
        System.err.println("IO problem when writing properties file");
        e.printStackTrace();
    }
    }
}
