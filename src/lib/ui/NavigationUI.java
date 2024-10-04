package src.lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
            NAVIGATION_UP,
            GO_TO_MAIN, //ios button
            MY_LISTS_BUTTON;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
    public void navigationUp(){
    //back to search results
        this.waitForElementAndClick(
               NAVIGATION_UP,
                "Cannot find Arrow_back button",
                5
        );
    }
    public void backToMainPageFromArticle(){
        this.navigationUp();
        //back to main page
        this.navigationUp();
    }

    public void backToMain(){
        this.waitForElementAndClick(GO_TO_MAIN,
                "Cannot find Wikipedia, return to Explore button",
                5);
    }

    public void clickMyLists(){
        this.waitForElementAndClick(
               MY_LISTS_BUTTON,
                "Cannot find Saved articles button",
                5);
    }

}
