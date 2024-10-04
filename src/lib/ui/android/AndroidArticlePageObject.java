package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
        SUBTITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']";
        SUBTITLE_BY_TPL = "xpath://*[@resource-id='pcs-edit-section-title-description' and @text='{TITLE}']";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        SAVE_ARTICLE_BUTTON = "xpath://android.widget.TextView[@content-desc='Save']";
        ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text='OK']";
        EXISTING_MY_LIST_FOLDER = "id:org.wikipedia:id/item_reading_list_statistical_description";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
