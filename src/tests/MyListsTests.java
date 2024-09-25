package src.tests;

import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.MyListsPageObject;
import src.lib.ui.NavigationUI;
import src.lib.ui.SearchPageObject;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForSubtitleElement();
        String article_subtitle = ArticlePageObject.getArticleSubtitle();

        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.backToMainPageFromArticle();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_subtitle);

    }

    @Test
    public void testSave2ArticlesToMyList(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line_1 ="Java";
        String search_line_2 = "Appium";
        SearchPageObject.typeSearchLine(search_line_1);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForSubtitleElement();
        String article_subtitle = ArticlePageObject.getArticleSubtitle();

        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.navigationUp();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.typeSearchLine(search_line_2);
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForSubtitleElement();
        String article_subtitle_2 = ArticlePageObject.getArticleSubtitle();
        ArticlePageObject.addSecondMoreArticleToMyList(name_of_folder);

        NavigationUI.backToMainPageFromArticle();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_subtitle);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_subtitle);
        MyListsPageObject.waitForArticleToAppearByTitle(article_subtitle_2);
        MyListsPageObject.openArticleFromMyList(article_subtitle_2);
        ArticlePageObject.assertElementHasTitle(article_subtitle_2);

    }
}
