package src.lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;
import src.lib.ui.ArticlePageObject;

import src.lib.ui.android.AndroidArticlePageObject;
import src.lib.ui.ios.iOSArticlePageObject;
import src.lib.ui.mobile_web.MWArticlePageObject;

public class ArticlePageObjectFactory {
    public static ArticlePageObject get(RemoteWebDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        }else if (Platform.getInstance().isIOS()){
            return new iOSArticlePageObject(driver);
        } else {
            return new MWArticlePageObject(driver);
        }
    }
}
