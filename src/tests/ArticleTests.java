package src.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import src.lib.CoreTestCase;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.SearchPageObject;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_subtitle = ArticlePageObject.getArticleSubtitle();
        assertEquals(
                "We see unexpected subtitle!",
                "Object-oriented programming language",
                article_subtitle);

    }

    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForSubtitleElement();
        ArticlePageObject.swipeToFooter();

    }

    @Test
    public void testCheckArticleTitlePresent(){
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_subtitle = ArticlePageObject.getArticleSubtitle();
        ArticlePageObject.assertElementHasTitle(article_subtitle);
    }
}
