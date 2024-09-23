package src.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.lib.CoreTestCase;
import src.lib.ui.SearchPageObject;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        assertTrue( "Less than 2 articles were found in the search results",
               driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size()>1);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
//    @Test
//    public void testFieldContainsText() {
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
//                "Cannot find Skip button on Welcome screen",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find 'Search Wikipedia'",
//                8
//        );
//        MainPageObject.assertElementHasText(
//                By.id("org.wikipedia:id/search_src_text"),
//                "Cannot find text: 'Search Wikipedia'",
//                "Search Wikipedia"
//
//        );
//
//    }
//    @Test
//    public void testWordSearchInArticles(){
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
//                "Cannot find Skip button on Welcome screen",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/search_container"),
//                "Cannot find 'Search Wikipedia'",
//                5
//        );
//        String search_word="Java";
//        MainPageObject.waitForElementAndSendKeys(
//                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
//                search_word,
//                "Cannot find search_text_input element",
//                25
//        );
//        List<WebElement> search_Results = driver.findElementsById("org.wikipedia:id/page_list_item_title");
//        assertFalse("No results found in the search", search_Results.isEmpty());
//
//        for (WebElement result : search_Results) {
//            String resultText = result.getText();
//            assertTrue("Result does not contain the search word: " + resultText,
//                    resultText.toLowerCase().contains(search_word.toLowerCase()));
//        }
//
//    }
    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("We found too few results",
                amount_of_search_results>0
        );
    }
    @Test
    public void testAmountOfEmptySearch(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "hgfhkfg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitEmptySearchResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
