# Automation Task

## Selenium-Cucumber-Java

This repository contains a collection of sample selenium-cucumber-java projects and libraries that demonstrate how to use the tool and develop automation scripts using the Cucumber BDD framework with Java as a programming language.

It automatically generates Surfire, Cucumber, HTML and JSON reporters as well.

It also generates screenshots for your tests if you enable it and also generates error shots for your failed test cases as well.

## Installation (pre-requisites)

JDK 1.8+ (make sure Java class path is set)

Maven (make sure .m2 class path is set)

IntelliJ/Eclipse

IntelliJ/Eclipse Plugins for

Maven

Cucumber

Git

# Framework set up

Fork / Clone repository from [here](https://github.com/eyupUK/cloudautomationtesting.git) or download zip and set it up in your local workspace.

## Framework Overview

The Cucumber BDD testing framework specifies acceptance tests as written from the view of the Product Owner. Using keywords such as Given, When, Then and And, acceptance criteria tests known as feature files can then be broken down into testable steps. Cucumber Selenium framework runs by specifying the test cases using tags that are to be run.

#### Cucumber Selenium - Overall test framework leveraging the Cucumber framework with Selenium 4 written in JAVA.

**pom.xml file** - It is used to download and upload libraries and tools using dependencies and builds that you will need in the framework.

**Feature File** - The feature file specifies the steps in the BDD language style.

**Hooks class** - Hooks class is the most important class as it performs the following functions and is used to run before and after each scenario. It is used to set up preconditions and clean up after the tests.

**Step Definition Classes** - Java class whereby the steps from the feature file are broken down to be coded into automation tests.

**Feature Model Class** - Java class whereby the step definition calls on methods that require action from the automated user such as entering text, finding/asserting fields on the UI

**Page Object Class** - Java class whereby the necessary HTML objects are captured as WebElements to be manipulated by the associated model class.

**ChromeDriver** - Local chrome driver necessary if needed to run independently.

**Cucumber Reports** - Cucumber has a built-in report generation whereby Feature files tested are automatically written to Cucumber’s own reporting system.

**Utilities** package may consist of UI/Database/API/Excel/MS Office/Driver/ConfigReader utility classes including testing methods that are used often in Singleton Model.

**Configuration Reader** - CR class reads the properties file Configuration.properties.

**configuration.properties** - It is used to manage credentials and access points that are used in Singleton Model. It is also used to manage the browser type and the URL of the environment to be tested.



# Run Some Sample Tests

Open terminal (MAC OSX) or command prompt / power shell (for windows OS) and navigate to the project directory type **mvn verify** or **mvn test** command to run features. With this command, it will invoke the default Chrome browser and will execute the tests.

To run features on the specific browser to be used, mvn verify "-Dbrowser=browser_name" browser_name can be one of the following but make sure that the browser‚  driver file is present and specified in the system variable. -- firefox -- chrome -- safari etc.

**Please note** that browser drivers are **not** included as part of this framework. The reason for not including is that Selenium browser driver versions are varies based on the browser version that you are using and also the Selenium server version.

To run specific feature if you have multiple feature files use, **mvn test -Dcucumber.options="classpath:features/my_first.feature"**

**Please note** that this framework does not include any sensitive data such as URL, username and password. You need to provide URL in the configuration.properties file and your credentials in the feature file to run this framework.

## Reporters

Once you run your tests, the framework generates the various types of reports taking screenshots of failures and errors automatically. This framework selenium-cucumber-java uses several different types of test reporters to communicate pass/failure.

Cucumber reports are automatically generated after the execution. To generate allure report, type in the terminal: **allure generate --clean** OR to view on the default browser immediately: **allure serve**

Please note that if you are facing JAVA_HOME issue on mac OS, then you need to set environment variable in your bash_profile by typing **source ~/.zshenv**

## Allure Report commands and options:

### Usage: allure [options]

Options:

--help    (Print commandline help.)

-q, --quiet   (Switch on the quiet mode.)

Default: false

-v, --verbose   (Switch on the verbose mode.)

Default: false

--version   (Print commandline version.)

Default: false


### Usage: generate [options]

Options:

-c, --clean     (Clean Allure report directory before generating a new one.)

Default: false

--config     (Allure commandline config path. If specified overrides values from)

--profile and --configDirectory --configDirectory      (Allure commandline configurations dire ctory. By default uses ALLURE_HOME directory.)

--profile    (Allure commandline configuration profile.)

-o, --report-dir, --output    (The directory to generate Allure report into.)

Default: allure-report

### Usage: serve [options]
Usage: serve [options] The directories with allure results
Options:
--config
Allure commandline config path. If specified overrides values from
--profile and --configDirectory.
--configDirectory
Allure commandline configurations directory. By default uses
ALLURE_HOME directory.
-h, --host
This host will be used to start web server for the report.
-p, --port
This port will be used to start web server for the report.
Default: 0
--profile
Allure commandline configuration profile.

### Usage: open [options]
Usage: open [options] The report directory
Options:
-h, --host
This host will be used to start web server for the report.
-p, --port
This port will be used to start web server for the report.
Default: 0

    
### Usage: plugin [options]
Options:
--config
Allure commandline config path. If specified overrides values from
--profile and --configDirectory.
--configDirectory
Allure commandline configurations directory. By default uses
ALLURE_HOME directory.
--profile
Allure commandline configuration profile.


Developed automation scripts using the BDD approach

Tests are written in the Cucumber framework using the Gherkin Syntax. More about Gherkin & Cucumber can be found at [https://cucumber.io/docs/reference](https://cucumber.io/docs/gherkin/) A typical test will look similar to this:

Feature: Performing a Yahoo Search

    As a user on the Yahoo search page
    I want to search for Selenium-Webdriver
    Because I want to learn more about it

    Background:

        Given I am on the search page

    Scenario: Performing a search operation
        When I enter "Selenium Webdriver" into the search box
        And  I click the search button
        Then I should see a list of search results

    Scenario Outline: Performing a search operation with passing test data as data table
        When I enter <searchItem> into the search box
        And  I click the search button
        Then I should see a list of search results

        Examples:
        |searchItem|
        |"Selenium Webdriver"|

# The Page Object Design Pattern

Within your Web App's UI there are areas that your tests interact. A Page Object simply models these as objects within the test code. This reduces the amount of duplicated code and means that if the UI changes, the fix need only be applied in one place. In other wards, one of the challenges of writing test automation is keeping your [selectors] ( classes, id's, or xpath' etc.) up to date with the latest version of your code. The next challenge is to keep the code you write nice and DRY (Don't Repeat Yourself). The page object pattern helps us accomplish this in one solution. Instead of including our selectors in our step definitions(in Cucumber) we instead place them in a .java file where we can manage all these selectors and methods together. Your test file should only call the test methods.

You can also place reusable methods or logic inside of these pages and call them from your step definitions java files. The page object serves as a layer of abstraction between tests and code. When A test fails, it fails on an individual step. That step may call a selector that is no longer valid, but that selector may be used by many other steps. By having a single source of truth of what the selector is supposed to be, fixing one selector on the page object could repair a number of failing tests that were affected by the same selector.

# Contribution

Create a fork of the project into your own repository. Make all your necessary changes and create a pull request with a description on what was added or removed and details explaining the changes in lines of code.

