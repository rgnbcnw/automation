@selenium
Feature: Try Scenario
  Scenario: Navigate to Toolshop Demo
    Given open url 'https://practicesoftwaretesting.com'
    When select to 'form-select' by value 'price,desc'
    And click 'checkbox' 'Hand Saw'
    And click card-body 'Wood Saw'
    And click Add to Cart button
    Then assert cart count