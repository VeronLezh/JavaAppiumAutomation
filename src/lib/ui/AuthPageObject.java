package src.lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthPageObject extends src.lib.ui.MainPageObject {
    public static final String
            LOGIN_BUTTON = "xpath://a[contains(@class, 'cdx-button--fake-button--enabled') and span[text()='Log in']]",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT =  "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button[name='wploginattempt']";

    public AuthPageObject(RemoteWebDriver driver){
        super(driver);
    }
    @Step("Click Auth button for mobile web")
    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find login button", 15);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find login button and click", 5);
    }
    @Step("Enter login and password for mobile web")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT,login,"Cannot find login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find password input", 5);
    }
    @Step("Submit Login form for mobile web")
    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON,"Cannot find submit button in registration form", 5);
    }
    @Step("Login as a User in mobile web")
    public void LoginAsWikiUser(String login, String password){
        this.clickAuthButton();
        this.enterLoginData(login,password);
        this.submitForm();
    }

}
