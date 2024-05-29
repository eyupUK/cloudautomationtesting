Feature: Learning feature

    Background:
      Given go to login page
      When log in by inputting email and password
        | email | password |
        |       |          |
      And select the "Performance" Menu at the top
      And click Workspace
      And select My Development
      And click the Due Date button
      And adjust the due date "20"th of the next month

  @test_assignment
  Scenario: Progress bar should be updated correctly after answering the questions in Test Assignment
    When click the assessment name "Test Assignment"
    And click Start Module in new window opened
    Then verify the progress bar is "0% complete"
    And scroll down to the bottom of the page
    Then verify the progress bar is "33% complete"
    When click Next
    Then verify the progress bar is "66% complete"
    When click Next
    When select "7" in the question 1.1
    Then verify the progress bar is "75% complete"
    When input "world health organization" into the textbox in the question 1.2
    Then verify the progress bar is "83% complete"
    When select "Yes" in the question 1.3
    Then verify the progress bar is "91% complete"
    When click Next
    When select "East" in the question 2.1
    Then verify the progress bar is "100% complete"
    When click Next
    When click Complete
    When click Submit
    Then verify the progress bar is "Module Completed"
    Then verify the success message is "Congratulations! you passed the module"


  @BETA_features
  Scenario: Progress bar should be updated correctly after answering the questions in BETA features
    When click the assessment name "BETA features"
    And click Start Module in new window opened
    Then verify the progress bar is "25% complete"
    When click Next
    And scroll down to the bottom of the page
    Then verify the progress bar is "50% complete"
    When click Next
    When click Next
    Then verify the progress bar is "51% complete"
    When click Next
    Then verify the progress bar is "52% complete"
    When click Next
    Then verify the progress bar is "54% complete"
    When click Next
    Then verify the progress bar is "55% complete"
    When click Next
    Then verify the progress bar is "56% complete"
    # solve the quiz












