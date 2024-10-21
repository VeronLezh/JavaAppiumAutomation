package src.lib.ui.mobile_web;

import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.SearchPageObject;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT ="css:#searchIcon";
        SEARCH_INPUT ="css:form>input[type='search']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_SUBSTRING_DESC ="xpath://li[@title='{TITLE}']//div[contains(text(), '{DESCRIPTION}')]";
        SEARCH_CANCEL_BUTTON="css:button.cancel";
        SEARCH_RESULT_ELEMENT ="css:ul.mw-mf-page-list>li.page-summary";
        EMPTY_SEARCH_RESULTS_LABEL ="css:p.without-results";
    }

    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }

}
