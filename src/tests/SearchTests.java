package src.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factories.SearchPageObjectFactory;

import java.util.List;

@Epic("Tests for Search")
public class SearchTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test search finds the result")
    @Severity(value= SeverityLevel.BLOCKER)
    @Step("Starting test testSearch")
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test for cancel search")
    @Severity(value= SeverityLevel.NORMAL)
    @Step("Starting test testCancelSearch")
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        if (Platform.getInstance().isAndroid()){
            Assert.assertTrue( "Less than 2 articles were found in the search results",
                    driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size()>1);
        } else {
            Assert.assertTrue( "Less than 2 articles were found in the search results",
                    driver.findElements(By.xpath("//XCUIElementTypeCollectionView[1]/XCUIElementTypeCell")).size()>1);
        }

        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Test text 'Search Wikipedia' is in the search placeholder")
    @Severity(value= SeverityLevel.MINOR)
    @Step("Starting test testFieldContainsText")
    public void testFieldContainsText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.checkElementHasText("Search Wikipedia");

    }
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Check search word presents in the search results")
    @Severity(value= SeverityLevel.CRITICAL)
    @Step("Starting test testWordSearchInArticles")
    public void testWordSearchInArticles(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        String search_line = "Java";
        List<WebElement> search_Results = driver.findElementsById("org.wikipedia:id/page_list_item_title");
        Assert.assertFalse("No results found in the search", search_Results.isEmpty());
        for (WebElement result : search_Results) {
            String resultText = result.getText();
            Assert.assertTrue("Result does not contain the search word: " + resultText,
                    resultText.toLowerCase().contains(search_line.toLowerCase()));
        }

    }
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Check Not empty search results")
    @Severity(value= SeverityLevel.NORMAL)
    @Step("Starting test testAmountOfNotEmptySearch")
    public void testAmountOfNotEmptySearch(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue("We found too few results",
                amount_of_search_results>0
        );
    }
    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Check Empty search results")
    @Severity(value= SeverityLevel.NORMAL)
    @Step("Starting test testAmountOfEmptySearch")
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
    @Features(value={@Feature(value="Search")})
    @DisplayName("Check 3 expected articles present in search results")
    @Description("Search by title and description only")
    @Severity(value= SeverityLevel.NORMAL)
    @Step("Starting test testAmountOfEmptySearch")
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
