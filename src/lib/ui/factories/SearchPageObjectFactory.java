package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;
import src.lib.ui.SearchPageObject;
import src.lib.ui.android.AndroidSearchPageObject;
import src.lib.ui.ios.iOSSearchPageObject;
import src.lib.ui.mobile_web.MWSearchPageObject;

public class SearchPageObjectFactory {
    public static SearchPageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidSearchPageObject(driver);
        }else if(Platform.getInstance().isIOS()){
            return new iOSSearchPageObject(driver);
        } else {
        return new MWSearchPageObject(driver);
    }
    }
}
