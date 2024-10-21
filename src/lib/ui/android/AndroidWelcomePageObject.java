package src.lib.ui.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import src.lib.ui.WelcomePageObject;

public class AndroidWelcomePageObject extends WelcomePageObject {
    static {
        NEXT_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_forward_button']";
        SKIP_BUTTON = "xpath://*[contains(@resource-id,'fragment_onboarding_skip_button')]";
        STEP_READING_LIST ="xpath://android.widget.TextView[@resource-id='org.wikipedia:id/primaryTextView' and @text='Reading lists with sync']";
        STEP_NEW_WAY_TO_EXPLORE = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/primaryTextView' and @text='New ways to explore']";
        STEP_ADD_OR_EDIT_LANGUAGES = "xpath://*[@resource-id='org.wikipedia:id/addLanguageButton']";
        STEP_DATA_PRIVACY = "xpath://android.widget.TextView[@resource-id='org.wikipedia:id/primaryTextView' and @text='Data & Privacy']";
        GET_STARTED_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/fragment_onboarding_done_button']";
    }

    public AndroidWelcomePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
