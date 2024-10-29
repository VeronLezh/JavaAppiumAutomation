package src.tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import src.lib.CoreTestCase;
import src.lib.Platform;
import src.lib.ui.WelcomePageObject;
import src.lib.ui.factories.WelcomePageObjectFactory;

@Epic("Tests for Welcome screen")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Step("Starting test testPassThroughWelcome")
    @Features(value={@Feature(value="WelcomeScreen")})
    @DisplayName("Pass all steps in Welcome screen")
    @Severity(value= SeverityLevel.CRITICAL)
    public void testPassThroughWelcome()
    {
        if (Platform.getInstance().isMW()){
            return;
        }
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
