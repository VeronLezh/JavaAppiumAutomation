package src.lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.Platform;

abstract public class NavigationUI extends src.lib.ui.MainPageObject {
    protected static String
            NAVIGATION_UP,
            GO_TO_MAIN, //ios button
            MY_LISTS_BUTTON,
            OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver)
    {
        super(driver);
    }
    @Step("Click back button")
    public void navigationUp(){
    //back to search results
        this.waitForElementAndClick(
               NAVIGATION_UP,
                "Cannot find Arrow_back button",
                5
        );
    }
    @Step("Back To Main Page from Article page")
    public void backToMainPageFromArticle(){
        this.navigationUp();
        //back to main page
        this.navigationUp();
    }

    @Step("Back to Main Page from Article page for iOS device")
    public void backToMain(){
        this.waitForElementAndClick(GO_TO_MAIN,
                "Cannot find Wikipedia, return to Explore button",
                5);
    }

    @Step("Open saved articles")
    public void clickMyLists(){
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_BUTTON,
                    "Cannot find navigation button to My list",
                    10
            );
        } else {
        this.waitForElementAndClick(
               MY_LISTS_BUTTON,
                "Cannot find Saved articles button",
                5);}
    }

    @Step("Open Nav menu for mobile web")
    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION,
                    "Cannot find button to open navigation menu",
                    5);
        }else {
            System.out.println("Method openNavigation do nothing for platform "+Platform.getInstance().getPlatformVar());
        }
    }

}
