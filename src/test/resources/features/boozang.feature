@Selenium
Feature: Test Anything On thelab.boozang.com
  Scenario: Test The Speed Game
    Given open url 'https://thelab.boozang.com/speedGame'
    When click the 'Start Game' button
    And click the 'End Game' button
    Then assert the 'Success' message

  Scenario: Test The Yellow Or Blue
    Given open url 'https://thelab.boozang.com/yellowOrBlue'
    When click the 'Generate Color' button
    And choose what color is given
    Then assert the 'Success!' message
    
  Scenario: Test The Cat/Dog Image
    Given open url 'https://thelab.boozang.com/catOrDog'
    When click the 'Generate Image' button
    And choose what image is given
    Then assert the 'Success!' message