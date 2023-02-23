## Mobile Automation Assignment | Automation Framework Appium Java with TestNG by M.Ahmad Sohail

🔆 **SOME FEATURES IN FRAMEWORK**

1. Read Config from Properties file
2. Extent Report
3. Write Log to file
4. Record video and Screenshot test case
5. Read data test from Excel file
6. Base function in the package: utils, helpers
7. Main Keyword: MobileUtils (call common function)
8. Sample test all function in WebUI keyword
9. Page Object Model

### ✳️ **APK Under Test**
- E-Commerce App `General-Store.apk` placed at

  ```src/test/resources/apkfile/General-Store.apk```

### ✳️ **Pre-Requisite**
1. IntelliJ Or Any IDE
2. Maven
3. Appium
4. NodeJS
5. Selenium
6. AndroidSDK
7. Android Studio
8. All Environment variables Configured
9. JDK All Version (recommend JDK >=11)

### ✳️ **HOW TO USE**

**1. Clone the Git Repo**

- Take clone of the project using below command
```
git clone https://github.com/ahmedsheikh2/AppiumAutomationFramework.git
```

**2. Run Emulator in Android Studio**
- Open Android Studio
- Click on Tools
- Select Device Manager
- Create Android Device and Set its name in Config.properties file
- Run the Emulator

**3. Open the project in IntelliJ**

- Open pom.xml
- Make sure to download all the Maven dependencies by Maven Update
- Run `npm install` to install appium and appium drivers
- Run test case in suite XML (**src/test/resources/suites/Assignment.xml)
- ExtendReport will Open automatically at the end with Results

**4. Run the Test**
- Run test case from Maven pom.xml file using Profile assignment. Execute the command below
```
  mvn clean test -P assignment
```

**5. Tests & Test Cases**
- Test Cases can be found in the file `EcommerceAppTest` under below path
```src/test/java/ecommerce/com/projects/mobile/testcases/ECommerceAppTest.java```


- Test Case 1: Verify that Error message appears when Name field is empty and user click on Let's Shop button
- Test Case 2: Verify that user is able to select items, Add to Cart and verify that Item is added to the Cart

*Note:* Test Case 2 pick up rows from the excel sheet and iterates over it. Here i've used `DataProvider` to iterate the test over data