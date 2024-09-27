package src.lib.ui;

import io.appium.java_client.AppiumDriver;


public class SearchPageObject extends MainPageObject {

    private static final String
            SKIP_BUTTON = "xpath://android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]",
            SEARCH_INIT_ELEMENT = "xpath://android.widget.ImageView[@content-desc='Search Wikipedia']",
            SEARCH_INPUT = "xpath://*[contains(@text, 'Search Wikipedia')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_RESULT_BY_SUBSTRING_TITLE_SUBSTRING_DESC = "xpath://*[@resource-id='org.wikipedia:id/search_results_list'][" +
                    "    .//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}'] and" +
                    "    .//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{DESCRIPTION}']" +
                    "]",
            SEARCH_CANCEL_BUTTON="id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_title",
            SEARCH_RESULT_ELEMENT_DESCRIPTION = "id:org.wikipedia:id/page_list_item_description",
            EMPTY_SEARCH_RESULTS_LABEL = "xpath://*[@text='No results']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByTitleAndDescription(String substring1, String substring2){
        return SEARCH_RESULT_BY_SUBSTRING_TITLE_SUBSTRING_DESC
                .replace("{TITLE}", substring1)
                .replace("{DESCRIPTION}", substring2);
    }
    /* TEMPLATES METHODS */

    public void skipOnboarding()
    {
        this.waitForElementAndClick(
                SKIP_BUTTON,
                "Cannot find Skip button on Welcome screen",
                5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(
                SEARCH_INIT_ELEMENT,
                "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,
                "Cannot find and click search init element",
                5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT,
                search_line,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with substring "+ substring);
    }

    public void waitForElementByTitleAndDescription(String article_title,String article_description)
    {
        String search_result_xpath = getResultSearchByTitleAndDescription(article_title,article_description);
        this.waitForElementPresent(search_result_xpath,
                "Cannot find search result with title "+ article_title + " and description " + article_description,
                15);

    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button!",
                5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Cannot find and click search cancel button",
                5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(
                SEARCH_CANCEL_BUTTON,
                "Search cancel button still present",
                5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring "+ substring, 10);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitEmptySearchResultLabel(){
        this.waitForElementPresent(EMPTY_SEARCH_RESULTS_LABEL,
                "Cannot find empty_result label",15);

    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,
                "We supposed not to find any result");
    }

    public void checkElementHasText(String text) {
        this.assertElementHasText(
               SEARCH_INPUT,
                "Cannot find text: " + text,
                text);

    }
}
