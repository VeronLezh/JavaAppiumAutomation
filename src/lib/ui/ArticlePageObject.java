package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            SUBTITLE = "xpath://*[@resource-id='pcs-edit-section-title-description']",
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
            SAVE_ARTICLE_BUTTON = "xpath://android.widget.TextView[@content-desc='Save']",
            ADD_TO_LIST_BUTTON = "xpath://*[@text='Add to list']",
            MY_LIST_NAME_INPUT ="id:org.wikipedia:id/text_input",
            OK_BUTTON = "xpath://*[@text='OK']",
            EXISTING_MY_LIST_FOLDER = "id:org.wikipedia:id/item_reading_list_statistical_description";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
//no unique title id(only subtitle)
    public WebElement waitForSubtitleElement(){
        return this.waitForElementPresent(SUBTITLE,
                "Cannot find article title on page", 10);
    }

    public String getArticleSubtitle() {
        WebElement subtitle_element = waitForSubtitleElement();
        return subtitle_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "Cannot find the end of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                SAVE_ARTICLE_BUTTON,
                "Cannot find Save article button",
                5);
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find Add to list button",
                5
        );
        //in this Wiki version folder input is empty by default
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot enter text into list folder input",
                5
        );
        this.waitForElementAndClick(
                OK_BUTTON,
                "Cannot find Add to list button",
                5
        );
    }

    public void addSecondMoreArticleToMyList(String name_of_folder){
        this.waitForElementAndClick(
                SAVE_ARTICLE_BUTTON,
                "Cannot find Save article button",
                5);
        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "Cannot find Add to list button",
                5
        );
        this.waitForElementAndClick(
                EXISTING_MY_LIST_FOLDER,
                "Cannot find the existing folder "+name_of_folder,
                5);
    }

    public void assertElementHasTitle(String article_title) {
        this.assertElementHasText(
                SUBTITLE,
                "Cannot find title " + article_title + " as article title",
                article_title);
    }

}
