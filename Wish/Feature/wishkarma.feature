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
    And user opens new indiviual application form...Employee:selectNewIndividualApplication
    And user fills the application details usind data (<firstname>;<surname>;<maidenname>;<idnumber>;<account>;<costcode>)...Employee:fillForm
    And user selects the check using data (<check1>)...Checks:selectCheck
    And user inputs to the check...Checks:input_Check_ITC
    And user clicks on submit...Checks:submitApplicationForm
    And user clicked on latest application tab...Index:clickLatestApplicationTab
    And user verifies the first name and last name in the latest application table using data (<firstname>;<surname>)...Index:validateFirstNameAndLastName
    And user logged out from application...Index:logout_impersonate
    And user enter his generated watermark number and validate...Login:verifyApplicaantWithWatermark
    And user login into application...Login:loginIntoApplication
    And user verifies the first name and last name in the application table using data (<firstname>;<surname>)...Index:validateFirstNameAndLastNameHome

    Examples: 
      | accountname | sidetabname     | firstname | surname | maidenname | idnumber      | check1                   | account   | costcode |
      | cloudseed   | Account User(s) | Sammy     | wish    | karma      | 6612115083087 | TransUnion Credit Report | Cloudseed | @        |

  @register
  Scenario Outline: Registration
    Given user navigates to URL...Login:navigateToURL
    When user clicks on register now link...Register:clickRegisterNow
    And user accepts welcome page terms and conditions...Register:acceptWelcomeTerms
    And user selects the registration type using data (<registrationtype>)...Register:selectRegistrationType
    And user selects industry value using data (<industry>)...Register:selectIndustry
    And user fills form for the registration type using data (<fieldsdata>)...Register:fillForm
    And user selects raido buttons in the form using data (<radiodata>)...Register:selectRadioButtons
    And user clicks on next in capture information page...Register:clickCaptureNext
    And user clicks on finish button...Register:clickFinish
    And user login into application...Login:loginIntoApplication
    And user activates the registered name using data (<fieldsdata>;<people soft number>;<pricing category>)...Index:activateRegistrationName

    Examples: 
      | registrationtype | industry  | fieldsdata        | radiodata         | people soft number | pricing category |
      | Corporate        | Education | Corporate_fields1 | Corporate_radios1 |            2555664 | Category A       |

  @pricing
  Scenario Outline: TC_10_Pricing Categories_Edit
    Given user navigates to URL...Login:navigateToURL
    And user login into application...Login:loginIntoApplication
    And user selects financial dropdown with option using data (<financialOption>)...Index:selectFinancialDropdown
    And user clicks on pricing category on which he wants to edit using data (<categoryname>)...Index:clickCategory
    And user clicks on pricing filter tab using data (<tabname>)...Index:selectPricingFilterTab
    And user edits check pricing details using data (<jsonfilekey1>)...Index:editCheckPricingDetails
    Then user clicks on save button...Index:clickCheckPricingSave

    Examples: 
      | financialOption    | categoryname | tabname       | jsonfilekey1           |
      | Pricing Categories | Category A   | Check Pricing | checkpricing_testdata1 |
      | Pricing Categories | Category B   | Check Pricing | checkpricing_testdata1 |

  @sa
  Scenario Outline: TC_13_Tool Manage SA Academics_AddInstitue
    Given user navigates to URL...Login:navigateToURL
    And user login into application...Login:loginIntoApplication
    And user selects financial dropdown with option using data (<toolsOption>)...Index:selectToolsDropdown
    And user clicks on add institution button using data (<institution>;<contactname>;<contactnumber>;<contactemail>;<additionalcharge>)...Index:addInstituionForm
    Then user searches and validates the added instituiton using data (<institution>)...Index:searchAndValidateAcademicInstituion

    Examples: 
      | toolsOption  | additionalcharge | institution | contactname | contactnumber | contactemail      |
      | SA Academics |              152 | Ganesh     | test        |     528741963 | test123@gmail.com |

  @sa @delete
  Scenario Outline: TC_13_Tool Manage SA Academics_DeleteInstitue
    Given user navigates to URL...Login:navigateToURL
    And user login into application...Login:loginIntoApplication
    And user selects financial dropdown with option using data (<toolsOption>)...Index:selectToolsDropdown
    And user deleted the institute from table using data (<institution>)...Index:deleteInstitute

    Examples: 
      | toolsOption  | additionalcharge | institution | contactname | contactnumber | contactemail      |
      | SA Academics |              152 | Ganesh     | test        |     528741963 | test123@gmail.com |
      
      