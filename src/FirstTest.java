package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
        capabilities.setCapability("appium:deviceName", "api26");
        capabilities.setCapability("appium:platformVersion", "8.0");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/veronikalezhneva/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    @After
    public void tearDown() {
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
//Improve testCancelSearch test for Hometask Lesson3 Ex.3
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
    public void testFieldContainsText()
    {
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

}
