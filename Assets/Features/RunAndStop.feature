Scenario: First run
    Given the app is closed
    When the player runs the app for the first time
    Then the screen shows the permissions screen

Scenario: Non first run
    Given the app is closed
    When the player runs the app not for the first time
    Then the screen shows the welcome screen