package src.lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {
    private static final String
    NEXT_BUTTON="xpath://XCUIElementTypeButton[@name='Next']",
    SKIP_BUTTON ="xpath://XCUIElementTypeButton[@name='Skip']",
    STEP_LEARN_MORE_LINK="xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAY_TO_EXPLORE="xpath://XCUIElementTypeStaticText[@name='New ways to explore']",
    STEP_ADD_OR_EDIT_LANGUAGES="xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
    STEP_LEARN_MORE_DATA_COLLECTED="xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
    GET_STARTED_BUTTON="xpath://XCUIElementTypeButton[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find Learn more Wikipedia link", 10);
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE,"Cannot find New ways to explore link", 10);
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_LANGUAGES,"Cannot find Add or edit preferred languages link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_DATA_COLLECTED,"Cannot find Learn more about data collected link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Cannot find Next button", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find Get Started button", 10);
    }

    public void clickSkip(){
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find Skip button", 5);
    }
}
