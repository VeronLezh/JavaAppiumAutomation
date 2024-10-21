package src.lib.ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;
import src.lib.ui.MyListsPageObject;
import src.lib.ui.android.AndroidMyListsPageObject;
import src.lib.ui.ios.iOSMyListsPageObject;
import src.lib.ui.mobile_web.MWMyListsPageObject;

public class MyListsPageObjectFactory {
    public static MyListsPageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidMyListsPageObject(driver);
        }else if (Platform.getInstance().isIOS()){
            return new iOSMyListsPageObject(driver);
        } else {
            return new MWMyListsPageObject(driver);
        }
    }
}
