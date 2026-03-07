Feature: Decathlon Footer Policy

  Scenario: Private Policy Validation
    Given the user is on the homepage of decathlon.com
    When The User scrolls down to the footer section
    And User clicks on "Privacy Policy" Link
    Then The page title should be "Decathlon | Privacy Policy"