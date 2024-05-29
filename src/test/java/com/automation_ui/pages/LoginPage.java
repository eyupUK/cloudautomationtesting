package com.automation_ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.automation_ui.utilities.BrowserUtils.waitForPageToLoad;

public class LoginPage extends BasePage{
    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button.btn.btn-primary.btn-login")
    private WebElement loginBtn;

    public void login(String username, String password){
        waitForPageToLoad(15);
        emailInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    @Override
    void handleIframe() {

    }
}
