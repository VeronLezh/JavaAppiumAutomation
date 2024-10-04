package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[contains(@text, '{TITLE}')]";
        NO_SAVED_ARTICLES_LABEL = "xpath://*[contains(@text, 'You have no articles added to this list.')]";
    }
    public AndroidMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
