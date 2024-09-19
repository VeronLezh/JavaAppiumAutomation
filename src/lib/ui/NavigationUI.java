package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{
    private static final String
            NAVIGATION_UP = "//android.widget.ImageButton[@content-desc='Navigate up']",
            MY_LISTS_BUTTON = "//android.widget.FrameLayout[@content-desc='Saved']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
    public void navigationUp(){
    //back to search results
        this.waitForElementAndClick(
                By.xpath(NAVIGATION_UP),
                "Cannot find Arrow_back button",
                5
        );
    }
    public void backToMainPageFromArticle(){
        this.navigationUp();
        //back to main page
        this.navigationUp();
    }

    public void clickMyLists(){
        this.waitForElementAndClick(
                By.xpath(MY_LISTS_BUTTON),
                "Cannot find Saved articles button",
                5);
    }

}
