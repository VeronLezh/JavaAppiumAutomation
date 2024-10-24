package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://XCUIElementTypeStaticText[@name='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{TITLE}')]";
        NO_SAVED_ARTICLES_LABEL = "id:No saved pages yet";
        CLOSE_SYNC_WINDOW = "id:Close";
        READING_LISTS = "xpath://XCUIElementTypeStaticText[@name='Reading lists']";
        SWIPE_ACTION_DELETE = "id:swipe action delete";
        SAVED_ARTICLE_LIST_ITEM="xpath://XCUIElementTypeCell";
    }
    public iOSMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}