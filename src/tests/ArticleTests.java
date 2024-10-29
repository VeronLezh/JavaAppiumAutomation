package src.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factories.ArticlePageObjectFactory;
import src.lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Compare article title with expected one")
    @Description("Open article 'Appium' and compare subtitle with expected")
    @Step("Starting test testCompareArticleTitle")
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_subtitle = ArticlePageObject.getArticleSubtitle();
        Assert.assertEquals(
                "We see unexpected subtitle!",
                "Automation for Apps",
                article_subtitle);

    }

    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Swipe article to the footer")
    @Description("Open article and swipe to the footer")
    @Step("Starting test testSwipeArticle")
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForSubtitleElement();
        ArticlePageObject.swipeToFooter();

    }

    @Test
    @Features(value={@Feature(value="Search"),@Feature(value="Article")})
    @DisplayName("Check article title present")
    @Step("Starting test testCheckArticleTitlePresent")
    public void testCheckArticleTitlePresent(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        String search_line = "Appium";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_subtitle = ArticlePageObject.getArticleSubtitleByTPL("Automation for Apps");
        ArticlePageObject.assertElementHasTitle(article_subtitle);
    }
}
