package src.lib.ui.factories;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;
import src.lib.ui.NavigationUI;
import src.lib.ui.android.AndroidNavigationUI;
import src.lib.ui.ios.iOSNavigationUI;
import src.lib.ui.mobile_web.MWNavigationUI;

public class NavigationUIFactory {
    public static NavigationUI get(RemoteWebDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndroidNavigationUI(driver);
        } else if (Platform.getInstance().isIOS()){
            return new iOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }
    }
}
