Feature: we test the user information
  Scenario: i don't want speak,without token
    Given name is "QB0004" and testId is "0001"
    When method is "get"
    Then assert result="GB2004"
  Scenario: i don't want speak anyway with token
    Given name is "QB0004" and testId is "0001"
    And token is "{token}"
    When method is "get"
    Then assert result="000000"
