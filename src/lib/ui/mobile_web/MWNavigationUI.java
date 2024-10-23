package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.NavigationUI;

public class MWNavigationUI extends NavigationUI {
    static {
        NAVIGATION_UP = "id:Back";
        MY_LISTS_BUTTON = "xpath://a[contains(@class, 'toggle-list-item__anchor') and span[text()='Watchlist']]";
        GO_TO_MAIN="id:Wikipedia, return to Explore";
        OPEN_NAVIGATION="css:#mw-mf-main-menu-button";
    }

    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}