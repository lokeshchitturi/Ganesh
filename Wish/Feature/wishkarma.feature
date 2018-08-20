Feature: Validating wishkarma functioalities

  Scenario Outline: Senrio 1
    Given user navigates to URL...Login:navigateToURL
    And user login into application...Login:loginIntoApplication
    When user click on Account in the menu bar...Index:clickAccountMenu
    And user enter the account name using data (<accountname>)...Index:enterAccountName
    And user clicks on search button...Index:clickSearch_Account
    And user click on account name in the table using data (<accountname>)...Index:clickAccountNameInTable
    And user click on side tab using data (<sidetabname>)...Index:clickSideTab
    And user impersonate the first user...Index:impersonateFirstEmployee
    #And user opens new indiviual application form...Employee:selectNewIndividualApplication
    #And user fills the application details usind data (<firstname>;<surname>;<maidenname>;<idnumber>;<account>;<costcode>)...Employee:fillForm
    #And user selects the check using data (<check1>)...Checks:selectCheck
    #And user inputs to the check...Checks:input_Check_ITC
    #And user clicks on submit...Checks:submitApplicationForm
    And user clicked on latest application tab...Index:clickLatestApplicationTab
    And user verifies the first name and last name in the latest application table using data (<firstname>;<surname>)...Index:validateFirstNameAndLastName
    And user logged out from application...Index:logout_impersonate
    #And user enter his generated watermark number and validate...Login:verifyApplicaantWithWatermark
    And user login into application...Login:loginIntoApplication
    And user verifies the first name and last name in the application table using data (<firstname>;<surname>)...Index:validateFirstNameAndLastNameHome
    
    
    

    Examples: 
      | accountname | sidetabname     | firstname | surname | maidenname | idnumber      | check1                   | account   | costcode |
      | cloudseed   | Account User(s) | Sammy     | wish    | karma      | 6612115083087 | TransUnion Credit Report | Cloudseed | @        |
