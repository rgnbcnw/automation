@selenium
Feature: Swag Labs
  Scenario Outline: Login to Swag Labs using different users
    Given open url https://www.saucedemo.com/
    When login using '<username>' and '<password>'
    Then user is logged in
    And sort the product by name z to a
    And add to cart the Sauce Labs Onesie
    And checkout
    And provide the FirstName and LastName
    And provide the ZIP
    And click continue
    Then assert the Checkout:Overview
    And click finish
    Then assert the Thank you for your order
    Examples:
      |username|password|
      |standard_user|secret_sauce|
#      |locked_out_user|secret_sauce|
#      |problem_user|secret_sauce|
      |performance_glitch_user|secret_sauce|
#      |error_user|secret_sauce|
      |visual_user|secret_sauce|
