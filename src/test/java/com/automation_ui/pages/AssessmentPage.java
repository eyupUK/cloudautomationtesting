package com.automation_ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import java.util.List;
import static com.automation_ui.utilities.BrowserUtils.*;

public class AssessmentPage extends BasePage{


    @FindBy(css = "gux-button.module-start-button.cover-art")
    private WebElement startModuleBtn;

    @FindBy(css = "gux-button#final-page-complete-button")
    private WebElement completeBtn;

    @FindBy(xpath = "//span[text()='Submit']")
    private WebElement submitBtn;

    @FindBy(css = "div.result-card-message")
    private WebElement successMsg;

    @FindBy(css = "div.content-scrollable")
    private WebElement richText;

    @FindBy(css = "gux-button.assignment-steps-navigate-next-button")
    private List<WebElement> nextBtn;

    @FindBy(css = ".editor-answer-option-label span")
    private List<WebElement> answerOptions;

    @FindBy(css = "textarea[placeholder='Add Response']")
    private WebElement addResponseTextArea;

    By progressBarLocator = By.cssSelector(".progress-bar-info");
    By progressBarCompleted = By.cssSelector(".progress-bar-complete");

    public String getSuccessMsg(){
        waitForPageToLoad(5);
        return successMsg.getText().trim().substring(0, 38);
    }

    public void clickSubmitBtn(){
        waitForPageToLoad(5);
        submitBtn.click();
    }

    public void clickCompleteBtn(){
        waitForPageToLoad(5);
        try {
            completeBtn.click();
        } catch (Exception e) {
            handleShadowElement("#final-page-complete-button", 10).click();
            e.printStackTrace();
        }
    }

    public void addResponse(String answer){
        handleIframe();
        try {
            addResponseTextArea.sendKeys(answer, Keys.TAB);
        } catch (Exception e) {
            handleShadowElement("textarea[placeholder='Add Response']", 5).sendKeys(answer, Keys.TAB);
            System.out.println("Exception occurred while adding response");
            e.printStackTrace();
        }
    }

    // handles the exception of ElementNotInteractableException: element not interactable
    public void clickNextBtn(){
        try {
            nextBtn.get(0).click();
        } catch (Exception e) {
            nextBtn.get(1).click();
            e.printStackTrace();
        }
    }

    public void clickAnswerOption(String answer){
        waitForPageToLoad(5);
        handleIframe();
        for (WebElement answerOption : answerOptions) {
            if (answerOption.getText().trim().equals(answer)) {
                answerOption.click();
                break;
            }
        }
    }


    public String getTextOfProgressBar(){
        driver.switchTo().defaultContent();
        waitFor(2); // wait for the progress bar to be updated
        List<WebElement> progressBarUpdate = driver.findElements(progressBarLocator);
        List<WebElement> progressBarComplete = driver.findElements(progressBarCompleted);
        if (!progressBarUpdate.isEmpty()) {
            if (!progressBarUpdate.get(0).getText().isEmpty()) {
                return progressBarUpdate.get(0).getText().trim();
            }
            else return progressBarUpdate.get(1).getText().trim();
        }
        else{
            for (WebElement element : progressBarComplete) {
                if (!element.getText().isEmpty()) {
                    return element.getText().trim().substring(0,16);
                }
            }
        }
        return "No progress message found";
    }


    /**
     * Clicks the start module button.
     * This method clicks the start module button on the assessment page.
     * It first waits for the page to load, then switches to the window with the title "Development and Feedback",
     * and finally clicks the start module button.
     */
    public void clickStartModule(){
        //waitForPageToLoad(10);
        switchToWindow("Development and Feedback");
        startModuleBtn.click();
    }


    /**
     * Scrolls the page down to the rich text element.
     * This method uses JavaScript to scroll the page down to the rich text element.
     * It first attempts to execute the scroll command, and if an exception occurs, it refreshes the page, waits for it to load,
     * and then attempts the scroll command again.
     *
     */
    public void scrollDownToRichText(){
        try {
            executeJScommand("arguments[0].scrollBy(0, 3099)", richText);

        } catch (Exception e) {
            driver.navigate().refresh();
            waitForPageToLoad(10);
            executeJScommand("arguments[0].scrollBy(0, 3099)", richText);
            e.printStackTrace();
        }
    }

    @Override
    protected void handleIframe() {
        waitForPageToLoad(10);
        waitForPresenceOfElement(By.cssSelector("#assessment-builder"), 10);
        driver.switchTo().frame("assessment-builder");
    }


}
