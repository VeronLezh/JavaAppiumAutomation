package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {
    private static final String
    NEXT_BUTTON="//XCUIElementTypeButton[@name='Next']",
    STEP_LEARN_MORE_LINK="//XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAY_TO_EXPLORE="//XCUIElementTypeStaticText[@name='New ways to explore']",
    STEP_ADD_OR_EDIT_LANGUAGES="//XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
    STEP_LEARN_MORE_DATA_COLLECTED="//XCUIElementTypeStaticText[@name='Learn more about data collected']",
    GET_STARTED_BUTTON="//XCUIElementTypeButton[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink()
    {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_LINK),"Cannot find Learn more Wikipedia link", 10);
    }

    public void waitForNewWayToExploreText()
    {
        this.waitForElementPresent(By.xpath(STEP_NEW_WAY_TO_EXPLORE),"Cannot find New ways to explore link", 10);
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(By.xpath(STEP_ADD_OR_EDIT_LANGUAGES),"Cannot find Add or edit preferred languages link", 10);
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_DATA_COLLECTED),"Cannot find Learn more about data collected link", 10);
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(By.xpath(NEXT_BUTTON),"Cannot find Next button", 10);
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON),"Cannot find Get Started button", 10);
    }
}
