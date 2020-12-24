Feature: get user verify code
  Scenario: we get the true
    Given name is "QB0001" and testId is "0001"
    When method is "post"
    Then assert result="000000"