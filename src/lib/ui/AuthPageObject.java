package src.lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthPageObject extends MainPageObject{
    public static final String
            LOGIN_BUTTON = "xpath://div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT =  "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button[name='wploginattempt']";

    public AuthPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find login button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find login button and click", 5);
    }

    public void enterLoginButton(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT,login,"Cannot find login input", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find password input", 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON,"Cannot find submit button in registration form", 5);
    }


}
