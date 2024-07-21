@Selenium
Feature: The Lab - Boozang
  Scenario: Test The Sorted List
    Given open url 'https://thelab.boozang.com/sortedList'
    When delete the 'Test 1' task
#    And delete the 'Test 2' task
#    And delete the 'Test 3' task
#    And delete the 'Test 4' task
#    And delete the 'Test 5' task
    When add the 'Test 1' task
    And add the 'Test 2' task
    And add the 'Test 3' task
    And add the 'Test 4' task
    And add the 'Test 5' task
    Then assert Your Schedule is full