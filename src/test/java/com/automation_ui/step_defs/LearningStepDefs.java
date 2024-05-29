package com.automation_ui.step_defs;

import com.automation_ui.pages.AssessmentPage;
import com.automation_ui.pages.HomePage;
import com.automation_ui.pages.LoginPage;
import com.automation_ui.utilities.ConfigurationReader;
import com.automation_ui.utilities.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import static com.automation_ui.utilities.BrowserUtils.takeScreenShot;

/**
 * This class contains the step definitions for the automation UI project.
 * It interacts with the web pages and performs actions based on the Cucumber scenarios.
 *
 * @author [eyupUK]
 * @version 1.0
 */
public class LearningStepDefs{

    WebDriver driver = Driver.get();
    String url = ConfigurationReader.get("url");


    @Given("go to login page")
    public void go_to_login_page() {
        driver.get(url);
    }


    @When("log in by inputting email and password")
    public void log_in_by_inputting_email_and_password(DataTable dataTable) {
        new LoginPage().login(dataTable.cell(1, 0), dataTable.cell(1, 1));
    }

    @When("select the {string} Menu at the top")
    public void select_the_menu_at_the_top(String menu) {
        new HomePage().selectMenu(menu);
    }

    @When("click Workspace")
    public void click_workspace() {
        new HomePage().clickWorkspace();
    }


    @When("select My Development")
    public void select_my_development() {
        new HomePage().clickMyDevelopment();
    }

    @When("click the Due Date button")
    public void click_the_due_date_button() {
        new HomePage().clickDueDay();
    }

    @When("adjust the due date \"{int}\"th of the next month")
    public void adjust_the_due_date_th_of_the_next_month(Integer dueDay) {
        new HomePage().clickRightArrowForNextMonth();
        new HomePage().selectDueDay(dueDay);
    }

    @When("click the assessment name {string}")
    public void click_the_assessment_name(String assessmentName) {
        new HomePage().clickAssessmentName(assessmentName);
    }

    @When("click Start Module in new window opened")
    public void click_start_module_in_new_window_opened() {
        new AssessmentPage().clickStartModule();
    }


    @When("scroll down to the bottom of the page")
    public void scroll_down_to_the_bottom_of_the_page() {
        new AssessmentPage().scrollDownToRichText();
    }


    @Then("verify the progress bar is {string}")
    public void verify_the_progress_bar_is(String expectedProgress) {
        String actualProgress = new AssessmentPage().getTextOfProgressBar();
        Assert.assertEquals("Progress bar not updated respectively!", expectedProgress, actualProgress);
    }


    @When("click Next")
    public void click_next() {
        new AssessmentPage().clickNextBtn();
    }


    @When("select {string} in the question {double}")
    public void select_in_the_question(String answer, Double questionNumber) {
        new AssessmentPage().clickAnswerOption(answer);
    }


    @When("input {string} into the textbox in the question {double}")
    public void input_into_the_textbox_in_the_question(String answer, Double questionNumber) {
        new AssessmentPage().addResponse(answer);
    }


    @When("click Complete")
    public void click_complete() {
        new AssessmentPage().clickCompleteBtn();
    }


    @When("click Submit")
    public void click_submit() {
        new AssessmentPage().clickSubmitBtn();
    }


    @Then("verify the success message is {string}")
    public void verify_the_success_message_is(String expectedMessage) {
        takeScreenShot();
        Assert.assertEquals("Success message is not as expected!", expectedMessage, new AssessmentPage().getSuccessMsg());
    }

}
