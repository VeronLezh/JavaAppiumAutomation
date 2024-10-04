package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import src.lib.Platform;
import src.lib.ui.MyListsPageObject;
import src.lib.ui.android.AndroidMyListsPageObject;
import src.lib.ui.ios.iOSMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        }else{
            return new iOSMyListsPageObject(driver);
        }
    }
}
