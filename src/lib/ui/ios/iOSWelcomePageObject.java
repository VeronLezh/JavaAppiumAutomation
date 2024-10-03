package src.lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import src.lib.ui.WelcomePageObject;

public class iOSWelcomePageObject extends WelcomePageObject {
    static {
        NEXT_BUTTON = "xpath://XCUIElementTypeButton[@name='Next']";
        SKIP_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']";
        STEP_NEW_WAY_TO_EXPLORE = "xpath://XCUIElementTypeStaticText[@name='New ways to explore']";
        STEP_ADD_OR_EDIT_LANGUAGES = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']";
        STEP_LEARN_MORE_DATA_COLLECTED = "xpath://XCUIElementTypeStaticText[@name='Learn more about our privacy policy and terms of use']";
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";
    }

    public iOSWelcomePageObject(AppiumDriver driver){

        super(driver);
    }
}
