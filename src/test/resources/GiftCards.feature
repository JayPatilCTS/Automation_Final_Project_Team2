Feature: Homepage Functionality

#  @TC_020
  Scenario: Handle Gift Card window and add to cart
    Given the user is on the homepage
    When the user clicks on "Gift Cards"
    And the user switches to the new gift card window
    And the user adds the gift card to the cart
    Then the gift card should be successfully added to the cart

#  @TC_021
#  Scenario Outline: Verify footer policy links
#    Given the user is on the homepage footer
#    When the user clicks on the "<PolicyLink>" link
#    Then the page should open with the title "<ExpectedTitle>"
#
#    Examples:
#      | PolicyLink     | ExpectedTitle  |
#      | Privacy Policy | Privacy Policy |
#      | Terms          | Terms of Use   |