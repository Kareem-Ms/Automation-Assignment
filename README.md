# TestAutomation_Assignment_UI

## The main Frameworks included in the project:
- Selenium
- Cucumber
- Json Reader for Data management

## Project Design:
- Page Object Model (POM) design pattern
- Data Driven framework
- Have a supporting Utilities package in src/main/java file path, named "utils" that includes many wrapper methods which services the project like ElementsAction class

## Steps to Execute Code
- Clone the code from the Repository 
- Open POM.xml file then reload that file to install dependecies
- Go to testrunner.java class under test\java\TestRunners folder then execute the class

## Code Explanation
- in the src/main/java/org you will find a package called "pages" this package used to include all the pages that will be used in testing so for example the "LoginPage" class contain methods and locators that exist in login page in order to apply POM design pattern
- in the src/main/java/org folder there is a package called "utils" this package contain helper classes like:
    - "ElementsAction" which is designed to handel find element after applying waits then make interactions with that element.
    - "PropertiesManager" this class contains methods to read from a property file which exist under src/main/resources to access something like BaseUrl
    - "JsonFileManager" this class is used to read data from json file to inject these data in the test classes
- in the src/test/java/org you will find a package called "testData" this package contains one json file per each test case to achieve isolation
