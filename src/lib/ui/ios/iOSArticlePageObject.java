package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        SUBTITLE ="xpath://XCUIElementTypeOther[@name='banner']/XCUIElementTypeOther/XCUIElementTypeStaticText[@name='Object-oriented programming language']";
        SUBTITLE_BY_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        FOOTER_ELEMENT = "id:View article in browser";
        SAVE_ARTICLE_BUTTON = "id:Save for later";
        ADD_TO_LIST_BUTTON = "id:add-to-list";
        CREATE_NEW_LIST_BUTTON="xpath://XCUIElementTypeButton[@name='Create a new list']";
        MY_LIST_NAME_INPUT = "xpath://XCUIElementTypeTextField[@value='reading list title']";
        OK_BUTTON = "xpath://XCUIElementTypeButton[@name='Create reading list']";
        EXISTING_MY_LIST_FOLDER = "xpath://XCUIElementTypeStaticText[@name='Learning programming']";
    }

    public iOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
