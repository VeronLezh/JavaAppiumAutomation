package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        SUBTITLE ="xpath://XCUIElementTypeStaticText[@name='Automation for Apps']";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_ARTICLE_BUTTON = "id:Save for later";
        ADD_TO_LIST_BUTTON = "id:add-to-list";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text='OK']";
        EXISTING_MY_LIST_FOLDER = "id:org.wikipedia:id/item_reading_list_statistical_description";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
