# itech2306-202005-lab-assignment1-team-zac-anush
itech2306-202005-lab-assignment1-team-zac-anush created by GitHub Classroom

V2.0 by Zac
- Made changes to Hospital property type
- Made changes to CalculatePropertyTypeRates operation

V2.1 by Zac
- Made changes to SchoolCommunity property type
- Made changes to CalculatePropertyTypeRates operation

V2.3 by Zac
- Fixed bug for showing 0-3 option when obtaining input for comunityCategory

V3.0 by Anush
- Implemented Industrial Property Type

V3.1 by Anush 
- Implemented Commercial Property Type

V3.2 by Zac   
- Pass in min value for obtainIntInput in CalculatePropertyTypeRates
- Modify author tags
- Tidy up some line spacing in the code

V4.1 by Zac
- Implement JUnit test case for SchoolCommunity class
- Fix setUpExtraServices method for SchoolCommunity class

V4.2 by Zac
- Remove lines of unused code

V4.3 by Zac
- Organise imports on SchoolCommunityTestCase

V4.4 by Zac
- Removed Arun's modification

V4.5 by Anush
- Added JUnit test case for Commercial class

V4.6 by Zac
- Implement PropertyTypeTestCaseRunner
- Fix imports on SchoolCommunityTestCase and CommercialTestCase
- Add comments for both JUnit test case

V4.7 by Zac
- Change @Before method name in commercialTestCase - setUp()

V4.8 by Zac
- Major changes in SchoolCommunityTestCase:
  - Break down test with multiple asserts into smaller test with only one
assert
  - Change @before method
  - Set up CIV only on test which require a CIV
  
V4.9 by Anush
- Improved PropertyTypeTestCaseRunner 

V4.10 by Zac
- Modify SchoolCommunityTestCase
  - Validate CIV for setCapitalImprovedValue() in Property class
  - Add tests for testing boundary value of CIV in SchoolCommunityTestCase
- Improve formatting of property type output

V4.11 by Zac
- Modify CommercialTestCase

V4.12 by Zac
- Modify formatting of output

V4.13 by Anush
- Removed console outputs to make the code more readable
- Simple fixes in the max and min values for both test cases

V4.14 by Zac
- Adding comments

V4.15 by Zac
- Adding .gitignore file

V5.1 by Zac
- First version for Assignment 2
  - Created utility package
  - Created LoadProperties class
  - Created LoadRatePayers class
  - Created new folder: files
  - Import two csv files

V5.2 by Zac
- LoadRatePayers class
  - Read ITECH2306_2020_Load_RatePayers.csv file using Scanner
  - Create ArrayList of RatePayer

V5.3 by Zac
- LoadRatePayer class
  - Minor editing

- LoadProperties class
  - Read ITECH2306_2020_Load_Properties.csv file using Scanner
  - Create ArrayList of Properties

V5.4 by Zac
- Edit ITECH2306_2020_Load_Properties.csv file
- Created Load_Properties.dat
- Created Load_RatePayer.dat

V5.5 by Zac
- Created new constructors on Property class and Residential class

V5.6 by Zac
- Chaining constructors on Prooperty class and subclasses
- Create new constructor on Commercial class
- Read Load_RatePayers.dat file into LoadProperties class

V5.7 by Zac
- Serializable file:
  - Write each RatePayer object one by one instead of writing the whole ArrayList

V5.8 by Zac
- Created QueryRatePayer class
- Partially implemented Requirement 4

V5.9 by Zac
- Continue implementing QueryRatePayer class
- Overriding equals method on RatePayer class

V5.10 by Anush
- Implemented try with resources to make the code cleaner

V5.11 by Anush
- Fixed errors with try with resources
- Added toString methods
- Added constructors
- Fixed data types for Property classes

V5.12 by Zac
- Tidying code
- Removed unwanted lines of code

V5.13 by Anush
- Implemented OtherProperty class
- Added constructor for OtherProperty class
- reformatted toString for better viewing

