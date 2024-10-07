package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {
        SKIP_BUTTON ="xpath://XCUIElementTypeButton[@name='Skip']";
        SEARCH_INIT_ELEMENT ="xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT ="xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeCell//XCUIElementTypeStaticText[contains(@value,'{SUBSTRING}')]";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_SUBSTRING_DESC ="xpath://XCUIElementTypeCollectionView[@visible='true']//XCUIElementTypeCell[.//XCUIElementTypeStaticText[@value='{TITLE}']] [.//XCUIElementTypeStaticText[contains(@value, '{DESCRIPTION}')]]";
        SEARCH_CANCEL_BUTTON="id:Clear text";
        SEARCH_RESULT_ELEMENT ="xpath://XCUIElementTypeCollectionView/XCUIElementTypeCell";
        EMPTY_SEARCH_RESULTS_LABEL ="id:No results found";
    }

    public iOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }

}
