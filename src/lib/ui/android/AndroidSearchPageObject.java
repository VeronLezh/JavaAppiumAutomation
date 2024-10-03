package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {
    static {
    SKIP_BUTTON ="xpath://android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]";
    SEARCH_INIT_ELEMENT ="xpath://android.widget.ImageView[@content-desc='Search Wikipedia']";
    SEARCH_INPUT ="xpath://*[contains(@text, 'Search Wikipedia')]";
    SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']";
    SEARCH_RESULT_BY_SUBSTRING_TITLE_SUBSTRING_DESC ="xpath://*[@resource-id='org.wikipedia:id/search_results_list']["+
            "    .//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and"+
            "    .//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']"+ "]";
    SEARCH_CANCEL_BUTTON="id:org.wikipedia:id/search_close_btn";
    SEARCH_RESULT_ELEMENT ="id:org.wikipedia:id/page_list_item_title";
    EMPTY_SEARCH_RESULTS_LABEL ="xpath://*[@text='No results']";
}


    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
