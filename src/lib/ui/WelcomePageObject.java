package src.lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class WelcomePageObject extends MainPageObject {
    protected static String
    NEXT_BUTTON,
    SKIP_BUTTON,
    STEP_LEARN_MORE_LINK,
    STEP_NEW_WAY_TO_EXPLORE,
    STEP_ADD_OR_EDIT_LANGUAGES,
            STEP_READING_LIST,//for android welcome
    STEP_LEARN_MORE_DATA_COLLECTED,
            STEP_DATA_PRIVACY,//for android welcome
    GET_STARTED_BUTTON;

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
        this.waitForElementPresent(STEP_LEARN_MORE_DATA_COLLECTED,"Cannot find Learn more about our privacy policy and terms of use", 10);
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

    public void waitForReadingList()
    {
        this.waitForElementPresent(STEP_READING_LIST,"Cannot find Reading list header", 10);
    }

    public void waitForDataPrivacy()
    {
        this.waitForElementPresent(STEP_DATA_PRIVACY,"Cannot find Data Privacy header", 10);
    }
}
