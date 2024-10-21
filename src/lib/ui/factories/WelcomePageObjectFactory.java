package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;
import src.lib.ui.WelcomePageObject;
import src.lib.ui.android.AndroidWelcomePageObject;
import src.lib.ui.ios.iOSWelcomePageObject;


public class WelcomePageObjectFactory {
    public static WelcomePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidWelcomePageObject(driver);
        }else{
            return new iOSWelcomePageObject(driver);
        }
    }
}
