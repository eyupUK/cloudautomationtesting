package com.automation_ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

import static com.automation_ui.utilities.BrowserUtils.*;


public class HomePage extends BasePage {

    @FindBy(css = ".top-level-menu-item-label")
    private List<WebElement> listOfMenu;

    @FindBy(css = "li[title='Workspace'][role='button']")
    private WebElement workspace;

    @FindBy(css = ".main-iframe.visible.ember-view > iframe")
    private WebElement iframe;

    @FindBy(id = "ember11")
    private WebElement myDevelopment;

    @FindBy(css = "button.current-date-display")
    private WebElement selectDateBtn;

    @FindBy(css = ".pika-next")
    private List<WebElement> rightArrowForNextMonth;

    @FindBy(css = ".apply-custom-range")
    private WebElement rightArrowForDateSubmission;

    @FindBy(linkText = "Test Assignment")
    private WebElement assessmentName;



    public void clickRightArrowForNextMonth() {
        rightArrowForNextMonth.get(1).click();
    }

    public void clickAssessmentName(String assessmentName) {
        By assessmentLocator = By.linkText(assessmentName);
        try {
            waitForClickability(assessmentLocator, 10).click();
        } catch (Exception e) {
            driver.navigate().refresh();
            waitForPageToLoad(10);
            clickAssessmentName(assessmentName);
            e.printStackTrace();
        }

    }

    public void selectDueDay(int dueDay){
        By dayLocator = By.xpath("//td[.='"+dueDay+"']/button");
        List<WebElement> days = driver.findElements(dayLocator);
        days.get(1).click();
        clickRightArrow();
    }

    public void clickDueDay() {
        waitForVisibility(By.cssSelector("button.current-date-display"), 10);
        selectDateBtn.click();
    }

    public void clickRightArrow() {
        rightArrowForDateSubmission.click();
    }

    public void clickWorkspace() {
        waitForClickability(By.cssSelector("li[title='Workspace'][role='button']"), 20);
        try {
            workspace.click();
        } catch (Exception e) {
            driver.navigate().refresh();
            waitForPageToLoad(10);
            clickWorkspace();
            e.printStackTrace();
        }
    }

    // it is switching to a frame and handling a shadow element
    public void clickMyDevelopment() {
        handleIframe();
        // shadow root element
        waitForClickability(handleShadowElement("#ember11", 10), 15).click();
    }


    public void selectMenu(String menu) {
        try {
            waitForPageToLoad(10);
            waitForClickability(By.xpath("//span[text()='Admin' and @class='top-level-menu-item-label']"), 20);
            switch (menu) {
                case "Activity":
                    waitForPresenceOfElement(By.xpath("//span[text()='Activity']"), 10);
                    listOfMenu.get(0).click();
                    break;
                case "Performance":
                    waitForPresenceOfElement(By.xpath("//span[text()='Performance']"), 10);
                    listOfMenu.get(1).click();
                    break;
                case "Admin":
                    waitForPresenceOfElement(By.xpath("//span[text()='Admin']"), 10);
                    listOfMenu.get(2).click();
                    break;
                default:
                    throw new RuntimeException("Invalid menu: " + menu);
            }
        } catch (Exception e) {
            driver.navigate().refresh();
            waitForPageToLoad(20);
            selectMenu(menu);
            e.printStackTrace();
        }
    }

    @Override
    protected void handleIframe() {
        waitForPageToLoad(10);
        waitForVisibility(By.cssSelector(".main-iframe.visible.ember-view > iframe"), 10);
        driver.switchTo().frame(iframe);
    }
}
