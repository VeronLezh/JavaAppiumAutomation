package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class WelcomePageObject extends src.lib.ui.MainPageObject {
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

    public WelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }

    @Step("Wait Learn more link")
    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,"Cannot find Learn more Wikipedia link", 10);
    }

    @Step("Wait New way to explore text")
    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE,"Cannot find New ways to explore link", 10);
    }

    @Step("Wait add or edit preferred language text")
    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_LANGUAGES,"Cannot find Add or edit preferred languages link", 10);
    }

    @Step("Wait Learn more about data collected text")
    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_DATA_COLLECTED,"Cannot find Learn more about our privacy policy and terms of use", 10);
    }

    @Step("Click Next button")
    public void clickNextButton()
    {
        this.waitForElementAndClick(NEXT_BUTTON,"Cannot find Next button", 10);
    }

    @Step("Click Get started button")
    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,"Cannot find Get Started button", 10);
    }

    @Step("Click Skip")
    public void clickSkip(){
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find Skip button", 5);
    }

    @Step("Wait Reading list text")
    public void waitForReadingList()
    {
        this.waitForElementPresent(STEP_READING_LIST,"Cannot find Reading list header", 10);
    }

    @Step("Wait Data privacy text")
    public void waitForDataPrivacy()
    {
        this.waitForElementPresent(STEP_DATA_PRIVACY,"Cannot find Data Privacy header", 10);
    }
}
