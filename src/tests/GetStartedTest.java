package src.tests;

import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.WelcomePageObject;
import src.lib.ui.factories.WelcomePageObjectFactory;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome()
    {
        WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
        if (Platform.getInstance().isIOS()){
            WelcomePageObject.waitForLearnMoreLink();
        } else {
            WelcomePageObject.waitForAddOrEditPreferredLangText();
        }
        WelcomePageObject.clickNextButton();
        WelcomePageObject.waitForNewWayToExploreText();
        WelcomePageObject.clickNextButton();

        if (Platform.getInstance().isIOS()){
            WelcomePageObject.waitForAddOrEditPreferredLangText();
        } else {
            WelcomePageObject.waitForReadingList();
        }
        WelcomePageObject.clickNextButton();

        if (Platform.getInstance().isIOS()){
            WelcomePageObject.waitForLearnMoreAboutDataCollectedText();
        } else {
            WelcomePageObject.waitForDataPrivacy();
        }
        WelcomePageObject.clickGetStartedButton();

    }
}
