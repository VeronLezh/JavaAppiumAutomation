package src;

import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import src.lib.CoreTestCase;
import src.lib.ui.MainPageObject;
import src.lib.ui.SearchPageObject;

import java.util.List;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception{
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

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
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
        
//        Assert.assertTrue( "Less than 2 articles were found in the search results",
//                driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size()>1);



//        MainPageObject.waitForElementNotPresent(
//                By.id("org.wikipedia:id/search_results_list"),
//                "Search results are still present on the page",
//                5);
}
    @Test
    public void testCompareArticleTitle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15);

        WebElement title_element = MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                15);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title);

    }
    @Test
    public void testSwipeArticle() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search_text_input element",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]"),
                "Cannot find 'Appium' topic",
                15
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Appium')]"),
                "Cannot find 'Appium' title",
                15
        );
        MainPageObject.swipeUpToFindElement(
               By.xpath("//*[@text='View article in browser']"),
               "Cannot find the end of the article",
               20
       );

    }
    @Test
    public void testFieldContainsText() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia'",
                8
        );
        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find text: 'Search Wikipedia'",
                "Search Wikipedia"

        );

    }
    @Test
    public void testWordSearchInArticles(){
        MainPageObject.waitForElementAndClick(
            By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
            "Cannot find Skip button on Welcome screen",
            5
    );
        MainPageObject.waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia'",
            5
    );
    String search_word="Java";
        MainPageObject.waitForElementAndSendKeys(
            By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
            search_word,
            "Cannot find search_text_input element",
            25
    );
    List<WebElement> search_Results = driver.findElementsById("org.wikipedia:id/page_list_item_title");
    Assert.assertFalse("No results found in the search", search_Results.isEmpty());

    for (WebElement result : search_Results) {
        String resultText = result.getText();
        Assert.assertTrue("Result does not contain the search word: " + resultText,
                resultText.toLowerCase().contains(search_word.toLowerCase()));
    }

}
    @Test
    public void testSaveFirstArticleToMyList(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15);
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                15);
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save article button",
                5);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find Add to list button",
                5
        );
        //in this Wiki version input is empty by default

        String name_of_folder="Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot enter text into list folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find Add to list button",
                5
        );
        //back to search results
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        //back to main page
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find Saved articles button",
                5);
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='"+name_of_folder+"']"),
                "Cannot find My list with articles",
                5);
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'You have no articles added to this list.')]"),
                "Article is still present",
                5
        );

    }
    @Test
    public void testAmountOfNotEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line = "Linkin Park Diskography";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search_text_input element",
                5
        );
        String search_result_locator = "org.wikipedia:id/page_list_item_title";
        MainPageObject.waitForElementPresent(
                By.id(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );
        int amount_of_search_results = MainPageObject.getAmountOfElements(
                By.id(search_result_locator)
        );
        Assert.assertTrue("We found too few results",
                amount_of_search_results>0
        );
    }
    @Test
    public void testAmountOfEmptySearch(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line = "hgfhkfg";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search_text_input element",
                5
        );
        String search_result_locator = "org.wikipedia:id/page_list_item_title";
        String empty_result = "//*[@text='No results']";
        MainPageObject.waitForElementPresent(
                By.xpath(empty_result),
                "Cannot find empty_result label",
                15
        );
        MainPageObject.assertElementNotPresent(
                By.id(search_result_locator),
                "We have found some results by request "+ search_line
        );
    }
    @Test
    public void testScreenRotationForArticlePage(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search_text_input element",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic " + search_line,
                15
        );
        String title_before_rotation=MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find 'Java (programming language)' title",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation=MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find 'Java (programming language)' title",
                15
        );
        Assert.assertEquals(
                "Title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation=MainPageObject.waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find 'Java (programming language)' title",
                15
        );
        Assert.assertEquals(
                "Title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );

    }
    @Test
    public void testCheckSearchArticleInBackground(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15
        );
        driver.runAppInBackground(Duration.ofSeconds(2));
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find article after returning foreground",
                15
        );

    }
    @Test
    public void testSave2ArticlesToMyList(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line_1 ="Java";
        String search_line_2 = "Appium";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line_1,
                "Cannot find search_text_input element "+search_line_1,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save article button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find Add to list button",
                5
        );
        String name_of_folder="Learning programming";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot enter text into list folder input",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find Add to list button",
                5
        );
        //back to search results
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        //Clear the previous search
        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search element",
                5
        );
        //search the second article
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line_2,
                "Cannot find search_text_input element "+search_line_2,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]"),
                "Cannot find " +search_line_2+" topic",
                15
        );
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, 'Appium')]"),
                "Cannot find " +search_line_2+" title",
                15
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save article button",
                5);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find 'Add to list' button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Cannot find the existing folder "+name_of_folder,
                5);
        //back to search results
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        //back to main page
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find 'Saved' articles button",
                5);
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='"+name_of_folder+"']"),
                "Cannot find My list with articles",
                5);
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );
        //check article 1 is NOT in the folder more
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']/[@text='Java (programming language)']"),
                "Article is still present",
                5
        );
        //check article 2 is in the folder
        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text, '"+search_line_2+"')]"),
                "Article is not present",
                5
        );
        //open article 2
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, '"+search_line_2+"')]"),
                "Article is not present",
                5
        );
        MainPageObject.assertElementHasText(
                By.xpath("//*[@text='"+search_line_2+"']"),
                "Cannot find text header: "+search_line_2,
                search_line_2
        );

    }
    @Test
    public void testCheckArticleTitlePresent(){
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line_1 ="Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line_1,
                "Cannot find search_text_input element "+search_line_1,
                5
        );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15
        );
        MainPageObject.assertElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Article title is not found on the page"
        );
    }

}