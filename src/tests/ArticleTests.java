package src.tests;

import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factories.ArticlePageObjectFactory;
import src.lib.ui.factories.SearchPageObjectFactory;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_subtitle = ArticlePageObject.getArticleSubtitle();
        assertEquals(
                "We see unexpected subtitle!",
                "Automation for Apps",
                article_subtitle);

    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Appium");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForSubtitleElement();
        ArticlePageObject.swipeToFooter();

    }

    @Test
    public void testCheckArticleTitlePresent(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_subtitle = ArticlePageObject.getArticleSubtitleByTPL("Appium");
        ArticlePageObject.assertElementHasTitle(article_subtitle);
    }
}
