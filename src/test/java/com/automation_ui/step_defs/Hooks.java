package com.automation_ui.step_defs;

import com.automation_ui.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import java.time.Duration;

/**
 * This class contains hooks for setting up and tearing down the test environment.
 * It initializes the WebDriver instance, maximizes the window, sets an implicit wait,
 * and attaches a screenshot if a test fails.
 */
public class Hooks {

    /**
     * Private static WebDriver instance to hold the driver instance.
     */
    private WebDriver driver = Driver.get();

    /**
     * Initializes the WebDriver instance, maximizes the window, and sets an implicit wait.
     */
    @Before
    public void setUp(){
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    /**
     * Tears down the test environment by closing the WebDriver instance.
     * If a test fails, it attaches a screenshot to the scenario.
     * @param scenario The Cucumber scenario object.
     */
    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        Driver.closeDriver();
    }

}