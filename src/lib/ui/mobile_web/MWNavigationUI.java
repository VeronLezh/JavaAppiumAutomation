package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.NavigationUI;

public class MWNavigationUI extends NavigationUI {
    static {
        NAVIGATION_UP = "id:Back";
        MY_LISTS_BUTTON = "css:a[data-event-name='watchlist']";
        GO_TO_MAIN="id:Wikipedia, return to Explore";
        OPEN_NAVIGATION="css:#mw-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}