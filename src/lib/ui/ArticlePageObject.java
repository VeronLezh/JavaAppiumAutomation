package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import src.lib.Platform;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
            SUBTITLE,
            SUBTITLE_BY_TPL,
            FOOTER_ELEMENT,
            SAVE_ARTICLE_BUTTON,
            ADD_TO_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            OK_BUTTON,
            EXISTING_MY_LIST_FOLDER;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static String getArticleXpathByName(String article_title){
        return SUBTITLE_BY_TPL.replace("{TITLE}",article_title);
    }

    public void waitForArticleTitleElement(String article_title){
        String article_xpath = getArticleXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find article title " +article_title,
                5
        );
    }

    public WebElement waitForSubtitleElement(){
        return this.waitForElementPresent(SUBTITLE,
                "Cannot find article title on page", 10);
    }

    public String getArticleSubtitle() {
        WebElement subtitle_element = waitForSubtitleElement();
        if (Platform.getInstance().isAndroid()){
            return subtitle_element.getAttribute("text");
        } else {
            return subtitle_element.getAttribute("name");
        }
    }

    public String getArticleSubtitleByTPL(String article_title) {
        String article_xpath = getArticleXpathByName(article_title); // Generation Xpath for Article title

        WebElement subtitle_element = this.waitForElementPresent(
                article_xpath,
                "Cannot find subtitle for article " + article_title,
                10
        );
        if (Platform.getInstance().isAndroid()){
            return subtitle_element.getAttribute("text");
        } else {
            return subtitle_element.getAttribute("name");
        }
    }


    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20);
        } else{
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    20);
        }

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
        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    CREATE_NEW_LIST_BUTTON,
                    "Cannot find +Create new list button",
                    5
            );
        }
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
        String article_xpath = getArticleXpathByName(article_title);
        this.assertElementHasText(
                article_xpath,
                "Cannot find title " + article_title + " as article title",
                article_title);
    }

}
