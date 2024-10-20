Feature: creating a new checking account

  Scenario: Create a standard individual checking account

    Given the user is logged in as "denize.ele@gmail.com" "Estpassword123"
    When the user creates a new checking account with the following data
      |checkingAccountType |accountOwnership |accountName             |initialDepositAmount |
      |Standard Checking   | Individual      | Denize second checking | 10000.00            |

    Then the user should see the green "Successfully created new Standard Checking account named Denize second checking" message
    And the user should see newly added account card
      | accountName            | accountType       | ownership  | accountNumber | interestRate | balance   |
      | Denize second checking | Standard Checking | Individual | 486134537     | 0.0%         | 10000.00  |
    And the user should see the following transaction
      | date             | category | description               | amount    | balance   |
      | 2024-01-30 05:30 | Income   | 845325809 (DPT) - Deposit | 10000.00  | 10000.00  |
