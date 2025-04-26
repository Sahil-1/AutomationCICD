@tag
Feature: Purchase the order deom Ecommerce website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page
  
  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name 								 | password   | productName |
      | sahiltest1@gmail.com | Sahiltest1 | ZARA COAT 3 |
