package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        NAVIGATION_UP = "id:Back";
        MY_LISTS_BUTTON = "id:Saved";
        GO_TO_MAIN="id:Wikipedia, return to Explore";
    }

    public iOSNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
