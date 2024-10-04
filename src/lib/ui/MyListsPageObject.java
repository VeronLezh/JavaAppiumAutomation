package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import src.lib.Platform;

abstract public class MyListsPageObject extends MainPageObject{
    protected static String
    FOLDER_BY_NAME_TPL,
    ARTICLE_BY_TITLE_TPL,
    NO_SAVED_ARTICLES_LABEL,
    CLOSE_SYNC_WINDOW, //for ios
    READING_LISTS, //for ios
            SWIPE_ACTION_DELETE;//for ios

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
     private static String getSavedArticleXpathByName(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",article_title);
     }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        if (Platform.getInstance().isIOS()){
            this.openReadingLists();
        }
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder with articles by name " + name_of_folder,
                5);
    }
    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title "+article_title,
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article is still present with title "+article_title,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title ){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByName(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article"
        );
        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(SWIPE_ACTION_DELETE,"Cannot find SwipeDeleteButton");
        }
        this.waitForArticleToDisappearByTitle(article_title);

    }
     public void openArticleFromMyList(String article_title){
         this.waitForArticleToAppearByTitle(article_title);
         String article_xpath = getSavedArticleXpathByName(article_title);
         this.waitForElementAndClick(
                 article_xpath,
                 "Article is not present",
                 5
         );
     }
     public void closeSyncWindow(){
        this.waitForElementAndClick(CLOSE_SYNC_WINDOW,
                "Cannot find Close Sync Window button in Saved",
                5);
     }

     public void openReadingLists(){
         this.waitForElementAndClick(READING_LISTS,
                 "Cannot find Reading Lists tab in Saved",
                 5);
     }
}
