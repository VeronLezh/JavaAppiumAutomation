package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        NAVIGATION_UP = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        MY_LISTS_BUTTON = "xpath://android.widget.FrameLayout[@content-desc='Saved']";
    }


    public AndroidNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}
