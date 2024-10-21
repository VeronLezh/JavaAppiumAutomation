package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";
        NO_SAVED_ARTICLES_LABEL = "xpath://*[contains(@text, 'You have no articles added to this list.')]";
        SAVED_ARTICLE_LIST_ITEM="id:org.wikipedia:id/page_list_item_container";
    }
    public AndroidMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
