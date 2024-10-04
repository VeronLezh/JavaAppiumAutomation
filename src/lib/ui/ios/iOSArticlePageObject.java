package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        SUBTITLE ="xpath://XCUIElementTypeOther[@name='banner']/XCUIElementTypeOther/XCUIElementTypeStaticText[@name='Object-oriented programming language']";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_ARTICLE_BUTTON = "id:Save for later";
        ADD_TO_LIST_BUTTON = "id:add-to-list";
        CREATE_NEW_LIST_BUTTON="xpath://XCUIElementTypeButton[@name='Create a new list']";
        MY_LIST_NAME_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        EXISTING_MY_LIST_FOLDER = "id:org.wikipedia:id/item_reading_list_statistical_description";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
