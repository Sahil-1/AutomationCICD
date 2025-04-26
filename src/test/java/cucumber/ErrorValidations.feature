@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error Validation For Login
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrec email or password." message is displayed

    Examples: 
      | name 								 | password   |
      | sahiltest2@gmail.com | Sahiltest2 |
