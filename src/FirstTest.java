package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import java.util.Arrays;
import java.time.Duration;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUP() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "api29");
        capabilities.setCapability("appium:platformVersion", "10.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/veronikalezhneva/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @After
    public void tearDown() {
        if (driver.getOrientation().equals(ScreenOrientation.LANDSCAPE)) {
            driver.rotate(ScreenOrientation.PORTRAIT);
        }
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15);

    }
    @Test
    public void testCancelSearch() {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia'",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search_text_input element",
                20
        );
        Assert.assertTrue( "Less than 2 articles were found in the search results",
                driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size()>1);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search element",
                5
        );

        //in this Wiki app version no X button, '<-' used to cancel search module(no ID)
//        waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
//                "Cannot find Arrow_back btn to cancel Search Wikipedia",
//                5
//        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Search results are still present on the page",
                5);
    }
    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15);

        WebElement title_element = waitForElementPresent(
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
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Appium",
                "Cannot find search_text_input element",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]"),
                "Cannot find 'Appium' topic",
                15
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Appium')]"),
                "Cannot find 'Appium' title",
                15
        );
        swipeUpToFindElement(
               By.xpath("//*[@text='View article in browser']"),
               "Cannot find the end of the article",
               20
       );

    }
    @Test
    public void testFieldContainsText() {
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia'",
                8
        );
        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find text: 'Search Wikipedia'",
                "Search Wikipedia"

        );

    }
    @Test
    public void testWordSearchInArticles(){
    waitForElementAndClick(
            By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
            "Cannot find Skip button on Welcome screen",
            5
    );
    waitForElementAndClick(
            By.id("org.wikipedia:id/search_container"),
            "Cannot find 'Search Wikipedia'",
            5
    );
    String search_word="Java";
    waitForElementAndSendKeys(
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
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15);
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                15);
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save article button",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find Add to list button",
                5
        );
        //in this Wiki version input is empty by default

        String name_of_folder="Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot enter text into list folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find Add to list button",
                5
        );
        //back to search results
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        //back to main page
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find Saved articles button",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='"+name_of_folder+"']"),
                "Cannot find My list with articles",
                5);
        swipeElementToLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'You have no articles added to this list.')]"),
                "Article is still present",
                5
        );

    }
    @Test
    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line = "Linkin Park Diskography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search_text_input element",
                5
        );
        String search_result_locator = "org.wikipedia:id/page_list_item_title";
        waitForElementPresent(
                By.id(search_result_locator),
                "Cannot find anything by the request " + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(
                By.id(search_result_locator)
        );
        Assert.assertTrue("We found too few results",
                amount_of_search_results>0
        );
    }
    @Test
    public void testAmountOfEmptySearch(){
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line = "hgfhkfg";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search_text_input element",
                5
        );
        String search_result_locator = "org.wikipedia:id/page_list_item_title";
        String empty_result = "//*[@text='No results']";
        waitForElementPresent(
                By.xpath(empty_result),
                "Cannot find empty_result label",
                15
        );
        assertElementNotPresent(
                By.id(search_result_locator),
                "We have found some results by request "+ search_line
        );
    }
    @Test
    public void testScreenRotationForArticlePage(){
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line,
                "Cannot find search_text_input element",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic " + search_line,
                15
        );
        String title_before_rotation=waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "text",
                "Cannot find 'Java (programming language)' title",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation=waitForElementAndGetAttribute(
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
        String title_after_second_rotation=waitForElementAndGetAttribute(
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
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Java",
                "Cannot find search_text_input element",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15
        );
        driver.runAppInBackground(Duration.ofSeconds(2));
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find article after returning foreground",
                15
        );

    }
    @Test
    public void testSave2ArticlesToMyList(){
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line_1 ="Java";
        String search_line_2 = "Appium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line_1,
                "Cannot find search_text_input element "+search_line_1,
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find 'Java (programming language)' title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save article button",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find Add to list button",
                5
        );
        String name_of_folder="Learning programming";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot enter text into list folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot find Add to list button",
                5
        );
        //back to search results
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        //Clear the previous search
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search element",
                5
        );
        //search the second article
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line_2,
                "Cannot find search_text_input element "+search_line_2,
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[1]"),
                "Cannot find " +search_line_2+" topic",
                15
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Appium')]"),
                "Cannot find " +search_line_2+" title",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save article button",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Add to list']"),
                "Cannot find 'Add to list' button",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "Cannot find the existing folder "+name_of_folder,
                5);
        //back to search results
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        //back to main page
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find Arrow_back button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"),
                "Cannot find 'Saved' articles button",
                5);
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/item_title' and @text='"+name_of_folder+"']"),
                "Cannot find My list with articles",
                5);
        swipeElementToLeft(
                By.xpath("//*[contains(@text, 'Java (programming language)')]"),
                "Cannot find saved article"
        );
        //check article 1 is NOT in the folder more
        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']/[@text='Java (programming language)']"),
                "Article is still present",
                5
        );
        //check article 2 is in the folder
        waitForElementPresent(
                By.xpath("//*[contains(@text, '"+search_line_2+"')]"),
                "Article is not present",
                5
        );
        //open article 2
        waitForElementAndClick(
                By.xpath("//*[contains(@text, '"+search_line_2+"')]"),
                "Article is not present",
                5
        );
        assertElementHasText(
                By.xpath("//*[@text='"+search_line_2+"']"),
                "Cannot find text header: "+search_line_2,
                search_line_2
        );

    }
    @Test
    public void testCheckArticleTitlePresent(){
        waitForElementAndClick(
                By.xpath("//android.widget.Button[contains(@resource-id,'fragment_onboarding_skip_button')]"),
                "Cannot find Skip button on Welcome screen",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Search Wikipedia\"]"),
                "Cannot find element_to_init_search",
                5
        );
        String search_line_1 ="Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                search_line_1,
                "Cannot find search_text_input element "+search_line_1,
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_list']/android.view.ViewGroup[2]"),
                "Cannot find 'Java Object-oriented programming language' topic",
                15
        );
        assertElementPresent(
                By.xpath("//*[@resource-id='pcs-edit-section-title-description']"),
                "Article title is not found on the page"
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return this.waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = this.waitForElementPresent(by, error_message, 5);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String error_message, String expected_text ) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        Assert.assertTrue(error_message, element.getAttribute("text").equals(expected_text));
    }

//   protected void swipeUp(int timeOfSwipe){
//        TouchAction action = new TouchAction(driver);
//        Dimension size = driver.manage().window().getSize();
//        int x = size.width / 2;
//        int start_y = (int)(size.height * 0.8);
//        int end_y = (int)(size.height * 0.2);
////methods press(), moveTo(), и waitAction() expect types PointOption and WaitOptions,but not int.
//        action
//                .press(PointOption.point(x,start_y))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
//                .moveTo(PointOption.point(x,end_y))
//                .release()
//                .perform();
//    }

    protected void swipeUp(int timeOfSwipe) {
        // Create a PointerInput instance
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // Get the screen size
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        Sequence swipe = new Sequence(finger, 1);
        // Press in x,startY
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, start_y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // wait action
        swipe.addAction(new Pause(finger, Duration.ofMillis(timeOfSwipe)));
        // move to end_y
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), x, end_y));
        // unpress
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // perform
        driver.perform(Arrays.asList(swipe));
    }

    protected void swipeUpQuick(){
        swipeUp(300);
    }
    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){
            if (already_swiped>max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
//    protected void swipeElementToLeft(By by, String error_message){
//        WebElement element = waitForElementPresent(by, error_message,10);
//        int left_x = element.getLocation().getX();
//        int right_x = left_x + element.getSize().getWidth();
//        int upper_y = element.getLocation().getY();
//        int lower_y = upper_y + element.getSize().getHeight();
//        int middle_y=(upper_y+lower_y)/2;
//
//        TouchAction action = new TouchAction(driver);
//        action
//                .press(PointOption.point(right_x, middle_y))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
//                .moveTo(PointOption.point(left_x,middle_y))
//                .release()
//                .perform();
//
//    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        // Coordinates
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        //PointerInput initialization for screen interaction
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Press in right_x
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), right_x, middle_y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // wait action
        swipe.addAction(new Pause(finger, Duration.ofMillis(300)));

        // move to left_x
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), left_x, middle_y));

        // unpress
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // perform
        driver.perform(Arrays.asList(swipe));
    }
    private int getAmountOfElements(By by){
      List elements = driver.findElements(by);
      return elements.size();
    }

    private void assertElementNotPresent(By by, String error_message ){
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements>0){
            String default_message = "An element "+by.toString()+" supposed to be not present";
            throw new AssertionError(default_message+ " " +error_message);
        }
    }
    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,error_message,timeoutInSeconds);
        return element.getAttribute(attribute);
    }
    private void assertElementPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements == 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

}
