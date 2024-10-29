package src.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.ArticlePageObject;
import src.lib.ui.SearchPageObject;
import src.lib.ui.factories.ArticlePageObjectFactory;
import src.lib.ui.factories.SearchPageObjectFactory;

@Epic("Tests for native apps")
public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    @Step("Starting test testScreenRotationForArticlePage")
    @Features(value={@Feature(value="Search"),@Feature(value="Article"),@Feature(value="App Condition")})
    @Severity(value= SeverityLevel.NORMAL)
    @DisplayName("Test App Rotation screen")
    public void testScreenRotationForArticlePage(){
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation=ArticlePageObject.getArticleSubtitle();
        this.rotateScreenLandscape();
        String title_after_rotation=ArticlePageObject.getArticleSubtitle();

        Assert.assertEquals(
                "Title has been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );
        this.rotateScreenPortrait();
        String title_after_second_rotation=ArticlePageObject.getArticleSubtitle();

        Assert.assertEquals(
                "Title has been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Step("Starting test testCheckSearchArticleInBackground")
    @Features(value={@Feature(value="Search"),@Feature(value="App Condition")})
    @Severity(value= SeverityLevel.NORMAL)
    @DisplayName("Test turn App in background")
    public void testCheckSearchArticleInBackground(){
        if (Platform.getInstance().isMW()){
            return;
        }
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
