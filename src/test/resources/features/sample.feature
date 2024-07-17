@selenium
Feature: Sample Scenario
  Scenario: Navigate to Facebook Login
    Given open url 'https://www.facebook.com'
    When type 'regine.baconawa3@gmail.com' to 'email' by 'id'
    When type 'Beyk062423!' to 'pass' by 'name'
    Then click 'button' 'Log In'
