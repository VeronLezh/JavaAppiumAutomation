package src.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.SearchPageObject;
import src.lib.ui.AuthPageObject;
import src.lib.ui.NavigationUI;
import src.lib.ui.MyListsPageObject;
import src.lib.ui.factories.ArticlePageObjectFactory;
import src.lib.ui.factories.MyListsPageObjectFactory;
import src.lib.ui.factories.NavigationUIFactory;
import src.lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for Saved articles")
public class MyListsTests extends CoreTestCase {
    private static final String
            name_of_folder = "Learning programming",
            search_line_1 ="Java",
            search_line_2 = "Appium",
            substring1="Object-oriented programming language",
            substring2="Automation for Apps",
            login="VeronikaLezhneva",
            password="Qa_test123";

    @Test
    @Step("Starting test testSaveFirstArticleToMyList")
    @Features(value={@Feature(value="Search"),@Feature(value="Article"),@Feature(value="My Lists")})
    @DisplayName("Saving 1 article '{substring1}' to My list and Delete")
    @Severity(value= SeverityLevel.BLOCKER)
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_1);
        SearchPageObject.clickByArticleWithSubstring(substring1);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitleElement(substring1);
        String article_subtitle = ArticlePageObject.getArticleSubtitleByTPL(substring1);
        if (Platform.getInstance().isMW()){
            ArticlePageObject.addArticleToWatchlist();
            src.lib.ui.AuthPageObject Auth = new AuthPageObject(driver);
            Auth.LoginAsWikiUser(login,password);

            ArticlePageObject.waitForArticleTitleElement(substring1);
            Assert.assertEquals("We are not on the same page after login",
                    article_subtitle,
                    ArticlePageObject.getArticleSubtitle());
        } else {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            NavigationUI.backToMainPageFromArticle();
        } else if (Platform.getInstance().isIOS()) {
            NavigationUI.backToMain();
        }
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        src.lib.ui.MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()){
            MyListsPageObject.closeSyncWindow();
        }
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_subtitle);
    }

    @Test
    @Step("Starting test testSave2ArticlesToMyList")
    @Features(value={@Feature(value="Search"),@Feature(value="Article"),@Feature(value="My Lists")})
    @DisplayName("Saving 2 articles to My list and Delete 1 of them")
    @Severity(value= SeverityLevel.NORMAL)
    public void testSave2ArticlesToMyList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_1);
        SearchPageObject.clickByArticleWithSubstring(substring1);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleTitleElement(substring1);
        String article_subtitle = ArticlePageObject.getArticleSubtitleByTPL(substring1);
        if (Platform.getInstance().isMW()){
            ArticlePageObject.addArticleToWatchlist();
            AuthPageObject Auth = new AuthPageObject(driver);
            Auth.LoginAsWikiUser(login,password);

            ArticlePageObject.waitForArticleTitleElement(substring1);
            Assert.assertEquals("We are not on the same page after login",
                    article_subtitle,
                    ArticlePageObject.getArticleSubtitle());
        } else {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if (Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()){
            NavigationUI.navigationUp();
            SearchPageObject.clickCancelSearch();
            SearchPageObject.typeSearchLine(search_line_2);
            SearchPageObject.clickByArticleWithSubstring(substring2);
            ArticlePageObject.waitForArticleTitleElement(substring2);
            String article_subtitle_2 = ArticlePageObject.getArticleSubtitleByTPL(substring2);

            ArticlePageObject.addSecondMoreArticleToMyList(name_of_folder);
        } else {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(search_line_2);
            SearchPageObject.clickByArticleWithSubstring(substring2);
        }

        if (Platform.getInstance().isAndroid()) {
            NavigationUI.backToMainPageFromArticle();
        } else if (Platform.getInstance().isIOS()) {
            NavigationUI.backToMain();
        }
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()){
            MyListsPageObject.closeSyncWindow();
        }
        if ((Platform.getInstance().isIOS()) || (Platform.getInstance().isAndroid())) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        int amountOfArticlesBefore = MyListsPageObject.getAmountOfAddedArticles();
        System.out.println(amountOfArticlesBefore);
        MyListsPageObject.swipeByArticleToDelete(article_subtitle);
        MyListsPageObject.waitForArticleToDisappearByTitle(article_subtitle);
        int amountOfArticlesAfter = MyListsPageObject.getAmountOfAddedArticles();
        System.out.println(amountOfArticlesAfter);
        Assert.assertEquals("Article wasn't deleted",
                amountOfArticlesBefore - 1,
                amountOfArticlesAfter
        );

//        MyListsPageObject.waitForArticleToAppearByTitle(article_subtitle_2);
//        MyListsPageObject.openArticleFromMyList(article_subtitle_2);
//        ArticlePageObject.assertElementHasTitle(article_subtitle_2);

    }
}
