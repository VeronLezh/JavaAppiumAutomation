package src.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factories.SearchPageObjectFactory;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        if (Platform.getInstance().isAndroid()){
            assertTrue( "Less than 2 articles were found in the search results",
                    driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size()>1);
        } else {
            assertTrue( "Less than 2 articles were found in the search results",
                    driver.findElements(By.xpath("//XCUIElementTypeCollectionView[1]/XCUIElementTypeCell")).size()>1);
        }

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
    @Test
    public void testFieldContainsText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.checkElementHasText("Search Wikipedia");

    }
    @Test
    public void testWordSearchInArticles(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String search_line = "Java";
        List<WebElement> search_Results = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        assertFalse("No results found in the search", search_Results.isEmpty());
        for (WebElement result : search_Results) {
            String resultText = result.getText();
            assertTrue("Result does not contain the search word: " + resultText,
                    resultText.toLowerCase().contains(search_line.toLowerCase()));
        }

    }
    @Test
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
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
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "hgfhkfg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitEmptySearchResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchResultsByTitleAndDescription() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);

        // Check 3 results
        SearchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        SearchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
        SearchPageObject.waitForElementByTitleAndDescription("Java", "Island in Indonesia");
    }

}
