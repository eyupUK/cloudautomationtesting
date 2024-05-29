/**
 * Driver class to manage WebDriver instances for different browsers.
 *
 * @author [eyupUK]
 * @version 1.0
 */
package com.automation_ui.utilities;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Singleton class to get and manage WebDriver instances for different browsers.
 *
 * @author [eyupUK]
 * @version 1.0
 */
public class Driver {

    private Driver() {
    }

    /**
     * Singleton instance of the WebDriver.
     */
    private static WebDriver driver;

    /**
     * Get the WebDriver instance for the specified browser.
     *
     * @return the WebDriver instance for the specified browser.
     */
    public static WebDriver get() {

        if (driver == null) {
            String browserConfig = ConfigurationReader.get("browser");
            synchronized (Driver.class) {
                switch (browserConfig) {
                    case "chrome-local" -> {
                        System.setProperty("webdriver.chrome.driver", "browserdriver/chromedriver");
                        driver = new ChromeDriver();
                    }
                    case "chrome" -> {
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("use-fake-ui-for-media-stream");
                        driver = new ChromeDriver(options);
                    }
                    case "chrome-headless" -> {
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("headless");
                        driver = new ChromeDriver(options);
                    }
                    case "firefox" -> {
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        firefoxOptions.setAcceptInsecureCerts(true);
                        driver = new FirefoxDriver(firefoxOptions);
                    }
                    case "firefox-headless" -> {
                        FirefoxOptions options = new FirefoxOptions();
                        options.addArguments("-headless");
                        WebDriver driver = new FirefoxDriver(options);
                    }
                    case "edge" -> {
                        if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                            throw new WebDriverException("Your OS doesn't support Edge");
                        EdgeOptions edgeOptions = new EdgeOptions();
                        // add browser options
                        driver = new EdgeDriver(edgeOptions);
                    }
                    case "safari" -> {
                        if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                            throw new WebDriverException("Your OS doesn't support Safari");
                        SafariOptions safariOptions = new SafariOptions();
                        // add browser options
                        driver = new SafariDriver(safariOptions);
                    }
                }
            }
        }
        return driver;
    }

    /**
     * Close the WebDriver instance.
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}