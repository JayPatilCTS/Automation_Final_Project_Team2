Feature: Verify footer policy links on Homepage


  Scenario: Redirect to Privacy Policy page from footer
    Given User is on the Homepage
    When User scrolls down to the footer section
    And User clicks on "Privacy Policy" link
    Then The page title should be "Decathlon | Privacy Policy"
