Feature: With Intelligence Automated Test Suite

  Scenario: Successful user login
    Given I am on the login page
    When I enter valid username and password
    And I click the sign in button
    Then I should be successfully logged in and redirected to homepage

  Scenario: Failed user login
    Given I am on the login page
    When I enter an invalid username and password
    And I click the sign in button
    Then I should see error message "We didn't recognize the username or password you entered. Please try again or click here to reset your password."

  Scenario: Opening watchlist modal
    Given I am on the platform homepage
    When I click the "Go" button in "My watchlist"
    Then the Watchlist modal should open

  Scenario: Closing modal dialog
    Given the modal dialog is open
    When I click the close button marked with "X" in the top right
    Then the modal should close
    And I should return to the previous page

  Scenario: Navigation through explore link
    Given I am on the login page
    When I enter valid username and password
    And I click the sign in button
    Then I should be successfully logged in and redirected to homepage
    When I click the explore link in the main menu
    Then I should be redirected to the discover page

  Scenario: Performing successful search
    Given I am on the login page
    When I enter valid username and password
    And I click the sign in button
    Then I should be successfully logged in and redirected to homepage
    When I focus on the search box in the top right corner
    And I enter the search query "Investors"
    Then I should see the title "Displaying results for 'Investors'" and a list of results

  Scenario: Search with no results
    Given I am on any page of the platform
    When I focus on the search box in the top right corner
    And I enter the search query "abcdefghihgfedcba"
    Then I should see the message "Your search for 'abcdefghihgfedcba' did not match any documents."

  Scenario: Switching platform section
    Given I am on the platform homepage "https://platform.withintelligence.com/all/now"
    When I click the dropdown currently displaying "Allocate With"
    And I select "Private Credit" from the dropdown
    Then I should be redirected to "https://platform.withintelligence.com/pcfi/now"