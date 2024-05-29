package com.automation_ui.pages;



import com.automation_ui.utilities.Driver;
import io.github.sukgu.Shadow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage{
    WebDriver driver = Driver.get();
    public BasePage() {
        PageFactory.initElements(driver, this);
    }


    /**
     * This method handles shadow DOM elements.
     * It creates a new instance of Shadow class and sets the implicit wait for the driver.
     * Then it finds the element using the provided cssSelector.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     * @param cssSelector The cssSelector of the element to be found within the shadow DOM.
     * @param timeoutSeconds The timeout in seconds for the implicit wait.
     * @return The WebElement found within the shadow DOM.
     */
    protected WebElement handleShadowElement(String cssSelector, int timeoutSeconds){
        Shadow shadow = new Shadow(driver);
        shadow.setImplicitWait(timeoutSeconds);
        return shadow.findElement(cssSelector);
    }

    abstract void handleIframe();
}
