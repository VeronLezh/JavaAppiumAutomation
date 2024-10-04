package src.tests;

import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.MyListsPageObject;
import src.lib.ui.NavigationUI;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factories.ArticlePageObjectFactory;
import src.lib.ui.factories.MyListsPageObjectFactory;
import src.lib.ui.factories.NavigationUIFactory;
import src.lib.ui.factories.SearchPageObjectFactory;

public class MyListsTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForSubtitleElement();
        String article_subtitle = ArticlePageObject.getArticleSubtitle();
        ArticlePageObject.addArticleToMyList(name_of_folder);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()){
            NavigationUI.backToMainPageFromArticle();
        } else {
            NavigationUI.backToMain();
        }
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()){
            MyListsPageObject.closeSyncWindow();
        }
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_subtitle);
    }

    @Test
    public void testSave2ArticlesToMyList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line_1 ="Java";
        String search_line_2 = "Appium";
        SearchPageObject.typeSearchLine(search_line_1);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForSubtitleElement();
        String article_subtitle = ArticlePageObject.getArticleSubtitle();


        ArticlePageObject.addArticleToMyList(name_of_folder);

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.navigationUp();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.typeSearchLine(search_line_2);
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForSubtitleElement();
        String article_subtitle_2 = ArticlePageObject.getArticleSubtitle();
        ArticlePageObject.addSecondMoreArticleToMyList(name_of_folder);

        NavigationUI.backToMainPageFromArticle();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_subtitle);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_subtitle);
        MyListsPageObject.waitForArticleToAppearByTitle(article_subtitle_2);
        MyListsPageObject.openArticleFromMyList(article_subtitle_2);
        ArticlePageObject.assertElementHasTitle(article_subtitle_2);

    }
}
