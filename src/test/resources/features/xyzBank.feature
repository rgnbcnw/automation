@Selenium
Feature: XYZ Bank Transaction Testing
  Scenario: VALIDATE CUSTOMER TRANSACTION WITH VALID DATA
    Given open url 'https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login'
    When click on main menu 'Bank Manager Login'
    When click on 'Add Customer' in Menu tab
    And enter 'Test', 'Test' and '123456'
    Then click on Add Customer Button

    When click on 'Open Account' in Menu tab
    And selects 'Test Test' in 'userSelect' and 'Dollar' in 'currency'
    Then click on 'Home' button

    When click on main menu 'Customer Login'
    And select 'Test Test' in 'userSelect'
    Then click on 'Login' button
    
    When click on 'Deposit' in Menu tab
    And enter '20000' amount in 'Deposit'
    And assert 'Deposit Successful'

    When click on 'Withdrawal' in Menu tab
    And enter '20000' amount in 'Withdraw'
    And assert 'Transaction successful'

    When click on 'Transactions' in Menu tab
    And assert 'Debit' Transaction
    And assert 'Credit' Transaction
    Then click on 'Logout' button

    When click on 'Home' button
    When click on main menu 'Bank Manager Login'
    When click on 'Customers' in Menu tab
    And click on delete button
    When click on 'Home' button

