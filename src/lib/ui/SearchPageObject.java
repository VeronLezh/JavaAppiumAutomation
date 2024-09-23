package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SKIP_BUTTON = "//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]",
            SEARCH_INIT_ELEMENT = "//android.widget.ImageView[@content-desc='Search Wikipedia']",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL ="//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON="org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_ELEMENT = "org.wikipedia:id/page_list_item_title",
            EMPTY_SEARCH_RESULTS_LABEL = "//*[@text='No results']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void skipOnboarding()
    {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON),
                "Cannot find Skip button on Welcome screen",
                5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find search result with substring "+ substring);
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button!", 5);
    }

    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button still present", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring "+ substring, 10);
    }

    public int getAmountOfFoundArticles(){
        this.waitForElementPresent(
                By.id(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request ",
                15
        );
        return this.getAmountOfElements(By.id(SEARCH_RESULT_ELEMENT));
    }

    public void waitEmptySearchResultLabel(){
        this.waitForElementPresent(By.xpath(EMPTY_SEARCH_RESULTS_LABEL),"Cannot find empty_result label",15);

    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(By.id(SEARCH_RESULT_ELEMENT),"We supposed not to find any result");
    }

    public void checkElementHasText(String text) {
        this.assertElementHasText(
                By.xpath(SEARCH_INPUT),
                "Cannot find text: " + text,
                text);

    }
}
