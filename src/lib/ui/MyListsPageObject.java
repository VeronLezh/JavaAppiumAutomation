package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    public static final String
    FOLDER_BY_NAME_TPL = "//*[@resource-id='org.wikipedia:id/item_title' and @text='{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[contains(@text, '{TITLE}')]",
    NO_SAVED_ARTICLES_LABEL = "//*[contains(@text, 'You have no articles added to this list.')]";

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
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder with articles by name " + name_of_folder,
                5);
    }
    public void waitForArticleToAppearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByName(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title "+article_title,
                5
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByName(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article is still present with title "+article_title,
                5
        );
    }

    public void swipeByArticleToDelete(String article_title ){
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByName(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(article_title);

    }
     public void openArticleFromMyList(String article_title){
         this.waitForArticleToAppearByTitle(article_title);
         String article_xpath = getSavedArticleXpathByName(article_title);
         this.waitForElementAndClick(
                 By.xpath(article_xpath),
                 "Article is not present",
                 5
         );
     }
}
