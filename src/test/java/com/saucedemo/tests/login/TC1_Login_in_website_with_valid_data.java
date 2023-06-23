package com.saucedemo.tests.login;

import com.saucedemo.utilities.MyFileWriter;
import com.saucedemo.utilities.UserBuilder;
import org.testng.annotations.Test;
import com.saucedemo.pom.HomePage;
import com.saucedemo.pom.LoginPage;
import com.saucedemo.utilities.TestUtilities;

public class TC1_Login_in_website_with_valid_data extends TestUtilities {

    //todo  IN THE ENT COMMENTS!
    //The test uses the generated users from CSV file, initializes the web driver using the LoginPage object,
    // then calls the login method, then the validation method from the Homepage object

    @Test
    public void loginWithValidDataInput() {
        MyFileWriter.writeToLog("TC1: Login in website with valid data");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();
        HomePage homePage = loginPage.testUserLogin(UserBuilder.fullUsersList.get(0));
        homePage.homepageValidator();

    }
}
