package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            SUBTITLE = "//*[@resource-id='pcs-edit-section-title-description']",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_ARTICLE_BUTTON = "//android.widget.TextView[@content-desc='Save']",
            ADD_TO_LIST_BUTTON = "//*[@text='Add to list']",
            MY_LIST_NAME_INPUT ="org.wikipedia:id/text_input",
            OK_BUTTON = "//*[@text='OK']",
            EXISTING_MY_LIST_FOLDER = "org.wikipedia:id/item_reading_list_statistical_description";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
//no unique title id(only subtitle)
    public WebElement waitForSubtitleElement(){
        return this.waitForElementPresent(By.xpath(SUBTITLE),
                "Cannot find article title on page", 10);
    }

    public String getArticleSubtitle() {
        WebElement subtitle_element = waitForSubtitleElement();
        return subtitle_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(SAVE_ARTICLE_BUTTON),
                "Cannot find Save article button",
                5);
        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find Add to list button",
                5
        );
        //in this Wiki version folder input is empty by default
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot enter text into list folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OK_BUTTON),
                "Cannot find Add to list button",
                5
        );
    }

    public void addSecondMoreArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                By.xpath(SAVE_ARTICLE_BUTTON),
                "Cannot find Save article button",
                5);
        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find Add to list button",
                5
        );
        this.waitForElementAndClick(
                By.id(EXISTING_MY_LIST_FOLDER),
                "Cannot find the existing folder "+name_of_folder,
                5);
    }

    public void assertElementHasTitle(String article_title) {
        this.assertElementHasText(
                By.xpath(SUBTITLE),
                "Cannot find title " + article_title + " as article title",
                article_title);
    }





}
