package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.ArticlePageObject;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        SUBTITLE = "css:.mw-page-title-main";
        SUBTITLE_BY_TPL = "xpath://*[@resource-id='pcs-edit-section-title-description' and @text='{TITLE}']";
        FOOTER_ELEMENT = "css:#footer-info-copyright";
        SAVE_ARTICLE_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watch button";
        ADD_TO_LIST_BUTTON = "css:#ca-watch";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        OK_BUTTON = "xpath://*[@text='OK']";
        OPTIONS_REMOVE_FROM_MY_LIST="css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        EXISTING_MY_LIST_FOLDER = "id:org.wikipedia:id/item_reading_list_statistical_description";
    }

    public MWArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
