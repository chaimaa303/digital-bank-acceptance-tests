@Registration
Feature: Digital Bank Registration Page

  Background:
    Given the user with "jack@test.co" is not in DB
    And User navigates to Digital Bank siginup Page

  @Test
  Scenario: Positive Case. As a user, I want to successfully create Digital Bank account
    When User creates account with following fields
      | title | firstName | lastName | gender | dob        | ssn         | email       | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | termsCheckMark |
      | Mr.   | Jady      | Test     | M      | 12/12/1998 | 123-54-2228 | jady@test.co | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591008 | 2136591211  | 1126593008 | true           |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      | title | firstName | lastName | gender | dob        | ssn         | email       | password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone  | accountNonExpired | accountNonLocked | credentialsNonExpired | enabled |
      | Mr.   | Jady       | Test     | M      | 12/12/1998 | 123-54-2228 | jady@test.co | Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591008 | 2136591211  | 1126593008 | true              | true             | true                  | true    |

  @NegativeRegistrationCases
  Scenario Outline: Negative Case. As a Digital Bank Admin i want to make sure the user can not register without providing all valid data
    When User creates account with following fields
      | title   | firstName   | lastName   | gender   | dob   | ssn   | email   | password   | address   | locality   | region   | postalCode   | country   | homePhone   | mobilePhone   | workPhone   | termsCheckMark   |
      | <title> | <firstName> | <lastName> | <gender> | <dob> | <ssn> | <email> | <password> | <address> | <locality> | <region> | <postalCode> | <country> | <homePhone> | <mobilePhone> | <workPhone> | <termsCheckMark> |
    Then User should see the "<fieldWithError>" required field error message "<errorMessage>"

    Examples:
      | title | firstName | lastName | gender | dob | ssn | email | password | address | locality | region | postalCode | country | homePhone | mobilePhone | workPhone | termsCheckMark | fieldWithError | errorMessage                        |
      |       |           |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | title          | Please select an item in the list.  |
      | Mr.   |           |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | firstName      | Please fill out this field.         |
      | Mr.   | jad       |          |        |     |     |       |          |         |          |        |            |         |           |             |           |                | lastName       | Please fill out this field.         |
      | Mr.   | jad       | noah     |        |     |     |       |          |         |          |        |            |         |           |             |           |                | gender         | Please select one of these options. |
      | Mr.   | jad       | noah     | M      |     |     |       |          |         |          |        |            |         |           |             |           |                | dob            | Please fill out this field.         |