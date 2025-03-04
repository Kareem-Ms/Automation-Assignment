Feature: Manage Users
  Scenario: Verify Adding then Deleting the same User
    Given I am in login page
    When I login using a correct username and password
    And I click on Admin tab on the left side menu
    And I get the number of records found
    And I add user by filling the required data
    Then I verify that the number of records increased by 1
    When I delete the new user
    Then I verify that the number of records decreased by 1