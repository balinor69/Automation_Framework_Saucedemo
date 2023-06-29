package com.saucedemo.tests.login;

import com.saucedemo.pom.HomePage;
import org.testng.annotations.Test;
import com.saucedemo.pom.LoginPage;
import com.saucedemo.utilities.MyFileWriter;
import com.saucedemo.utilities.TestUtilities;
import com.saucedemo.utilities.UserBuilder;

public class TC3_Verify_locked_user_cant_login_in_website extends TestUtilities {
    @Test
    public void loginWithLockedUser() {
        MyFileWriter.writeToLog("TC3: Verify locked user cant login in website");

        LoginPage loginPage = new LoginPage(TestUtilities.driver);
        loginPage.loginPageValidator();
        HomePage homePage = loginPage.testUserLogin(UserBuilder.fullUsersList.get(1));
        loginPage.validateErrorMsgInvalidUser();
        homePage.homepageValidator();

    }
}
