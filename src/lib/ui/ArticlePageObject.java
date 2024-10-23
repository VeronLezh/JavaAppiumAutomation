package src.lib.ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;

abstract public class ArticlePageObject extends MainPageObject{
    protected static String
            SUBTITLE,
            TITLE_BY_TPL,
            FOOTER_ELEMENT,
            SAVE_ARTICLE_BUTTON,
            ADD_TO_LIST_BUTTON,
            CREATE_NEW_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            OK_BUTTON,
            EXISTING_MY_LIST_FOLDER,
            OPTIONS_REMOVE_FROM_MY_LIST;

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    private static String getArticleXpathByName(String article_title){
        return TITLE_BY_TPL.replace("{TITLE}",article_title);
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
        } else if(Platform.getInstance().isIOS()) {
            return subtitle_element.getAttribute("name");
        } else {
            return subtitle_element.getText();
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
        } else if(Platform.getInstance().isIOS()) {
            return subtitle_element.getAttribute("name");
        } else {
            return subtitle_element.getText();
        }
    }


    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
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

    public void removeArticleFromSavedIfAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(SAVE_ARTICLE_BUTTON,
                    "Cannot find button to add an article to saved list",
                    5);
        }
    }

    public void addArticleToWatchlist(){
        //this.removeArticleFromSavedIfAdded();
        this.waitForElementAndClick(
                SAVE_ARTICLE_BUTTON,
                "Cannot find Save article button",
                5);
    }

}
