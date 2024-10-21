package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.MyListsPageObject;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://XCUIElementTypeStaticText[@name='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        NO_SAVED_ARTICLES_LABEL = "id:No saved pages yet";
        CLOSE_SYNC_WINDOW = "id:Close";
        READING_LISTS = "xpath://XCUIElementTypeStaticText[@name='Reading lists']";
        SWIPE_ACTION_DELETE = "id:swipe action delete";
        SAVED_ARTICLE_LIST_ITEM="xpath://XCUIElementTypeCell";
        REMOVE_FROM_SAVED_BUTTON="xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";

    }
    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}

