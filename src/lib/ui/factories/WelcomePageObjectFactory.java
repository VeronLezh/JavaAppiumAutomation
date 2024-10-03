package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import src.lib.Platform;
import src.lib.ui.WelcomePageObject;
import src.lib.ui.android.AndroidWelcomePageObject;
import src.lib.ui.ios.iOSWelcomePageObject;


public class WelcomePageObjectFactory {
    public static WelcomePageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidWelcomePageObject(driver);
        }else{
            return new iOSWelcomePageObject(driver);
        }
    }
}
