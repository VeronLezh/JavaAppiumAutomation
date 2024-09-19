package src.lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{
    private static final String
            SUBTITLE = "//*[@resource-id='pcs-edit-section-title-description']",
            FOOTER_ELEMENT = "//*[@text='View article in browser']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
//no unique title id(only subtitle)
    public WebElement waitForSubtitleElement(){
        return this.waitForElementPresent(By.xpath(SUBTITLE),
                "Cannot find article title on page", 10);
    }

    public String getArticleSubtitle() {
        WebElement subtitle_element = waitForSubtitleElement();
        return subtitle_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20);
    }


}
