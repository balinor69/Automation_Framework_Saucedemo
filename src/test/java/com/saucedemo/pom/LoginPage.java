package com.saucedemo.pom;

import com.saucedemo.objects.TestUser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.saucedemo.utilities.MyFileWriter;
import com.saucedemo.utilities.TestUtilities;

public class LoginPage extends TestUtilities {
    protected WebDriver driver;
    private static String loginErrorMessage = "";
    private static final String LOGIN_PAGE = "Current page is Login page.";
    private static final String LOGIN_FORM_MISSING_MESSAGE = "Login form is missing!";
    private static final String MISSING_ELEMENT_MESSAGE = "Element ( %s ) is missing!";
    private static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private static final String HOME_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private static final String LOGIN_FAILED_MESSAGE = "Login Failed!";
    private static final String LOGIN_WRONG_USERNAME_MESSAGE = "Wrong username!";
    private static final String LOGIN_WRONG_PASSWORD_MESSAGE = "Wrong password!";
    private static final String LOGIN_SUCCESSFUL_MESSAGE = "Login is Successful!";
    private static final String LOGOUT_FAILED_MESSAGE = "Logout Failed!";
    private static final String LOGOUT_SUCCESSFUL_MESSAGE = "Logout is Successful!";
    private static final String DIFFERENT_MESSAGE = "The message is different!";
    private static final String WRONG_USER_AND_PASS_MESSAGE = "Epic sadface: Username and password do not match any user in this service";
    private static final String LOCKED_USER_MESSAGE = "Epic sadface: Sorry, this user has been locked out.";

    @FindBy(xpath = "//div[@id='login_button_container']")
    private WebElement loginForm;
    @FindBy(xpath = "//input[@id='user-name' and @placeholder='Username']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@id='password' and @placeholder='Password']")
    private WebElement userPasswordField;
    @FindBy(xpath = "//input[@id='login-button' and @type='submit']")
    private WebElement loginButton;
    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorText;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String returnErrorText() {
        return loginErrorMessage = errorText.getText();
    }

    public void validateErrorMsgWrongUsernameAndPassword() {
        Assert.assertEquals(errorText.getText(), WRONG_USER_AND_PASS_MESSAGE, DIFFERENT_MESSAGE);
    }

    public void validateErrorMsgInvalidUser() {
        Assert.assertEquals(errorText.getText(), LOCKED_USER_MESSAGE, DIFFERENT_MESSAGE);
    }

    public void loginPageValidator() {
        Assert.assertTrue(loginForm.isDisplayed(), LOGIN_FORM_MISSING_MESSAGE);
        Assert.assertTrue(userNameField.isDisplayed(), String.format(MISSING_ELEMENT_MESSAGE, userNameField));
        Assert.assertTrue(userPasswordField.isDisplayed(), String.format(MISSING_ELEMENT_MESSAGE, userPasswordField));
        Assert.assertTrue(loginButton.isDisplayed(), String.format(MISSING_ELEMENT_MESSAGE, loginButton));
    }

    public void successfulLogout() {
        if (driver.getCurrentUrl().equals(LOGIN_PAGE_URL)) {
            System.out.println(LOGOUT_SUCCESSFUL_MESSAGE);
            MyFileWriter.writeToLog(LOGOUT_SUCCESSFUL_MESSAGE);
            System.out.println(LOGIN_PAGE);
            MyFileWriter.writeToLog(LOGIN_PAGE);
        } else {
            System.out.println(LOGOUT_FAILED_MESSAGE);
            MyFileWriter.writeToLog(LOGOUT_FAILED_MESSAGE);
            Assert.assertEquals(driver.getCurrentUrl(), LOGIN_PAGE_URL, LOGOUT_FAILED_MESSAGE);
        }
    }

    public HomePage login(String username, String password) {
//        Actions actions = new Actions(driver);
//
//        WebElement userNameField = this.driver.findElement(By.xpath("//input[@id='user-name' and @placeholder='Username']"));
//        WebElement userPasswordField = this.driver.findElement(By.xpath("//input[@id='password' and @placeholder='Password']"));

        userNameField.clear();
        userNameField.click();
        userNameField.sendKeys(username);
//        actions.sendKeys(userNameField, username).perform();

        /* Validate the inserted data is the same with provided */
        Assert.assertEquals(username, this.userNameField.getAttribute("value"), LOGIN_WRONG_USERNAME_MESSAGE);
//        System.out.println(userNameField.getAttribute("value"));
//        System.out.println(username);


        userPasswordField.clear();
        userPasswordField.click();

        userPasswordField.sendKeys(password);
//        actions.sendKeys(userPasswordField, password).perform();

        /* Validate the inserted data is the same with provided */
        Assert.assertEquals(password, this.userPasswordField.getAttribute("value"), LOGIN_WRONG_PASSWORD_MESSAGE);
//        System.out.println(userPasswordField.getAttribute("value"));
//        System.out.println(password);

        loginButton.click();

        if (driver.getCurrentUrl().equals(LOGIN_PAGE_URL)) {
            System.out.println(LOGIN_FAILED_MESSAGE);
            System.out.println("Error text: " + returnErrorText());
            MyFileWriter.writeToLog(LOGIN_FAILED_MESSAGE + " - " + returnErrorText());

            Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, LOGIN_FAILED_MESSAGE);
        } else {
            System.out.println(LOGIN_SUCCESSFUL_MESSAGE);
            MyFileWriter.writeToLog(LOGIN_SUCCESSFUL_MESSAGE);

            Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, LOGIN_FAILED_MESSAGE);
        }

        return new HomePage(driver);
    }

    public HomePage testUserLogin(TestUser testUser) {

        userNameField.clear();
        userNameField.click();
        userNameField.sendKeys(testUser.getUsername());
        /* Validate the inserted data is the same with provided */
        Assert.assertEquals(testUser.getUsername(), userNameField.getAttribute("value"), LOGIN_WRONG_USERNAME_MESSAGE);

        userPasswordField.clear();
        userPasswordField.click();
        userPasswordField.sendKeys(testUser.getPassword());
        /* Validate the inserted data is the same with provided */
        Assert.assertEquals(testUser.getPassword(), userPasswordField.getAttribute("value"), LOGIN_WRONG_PASSWORD_MESSAGE);

        loginButton.click();

        if (driver.getCurrentUrl().equals(LOGIN_PAGE_URL)) {
            System.out.println(LOGIN_FAILED_MESSAGE);
            System.out.println("Error text: " + returnErrorText());
            MyFileWriter.writeToLog(LOGIN_FAILED_MESSAGE + " - " + returnErrorText());

            Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, LOGIN_FAILED_MESSAGE);
        } else {
            System.out.println(LOGIN_SUCCESSFUL_MESSAGE);
            MyFileWriter.writeToLog(LOGIN_SUCCESSFUL_MESSAGE);

            Assert.assertEquals(driver.getCurrentUrl(), HOME_PAGE_URL, LOGIN_FAILED_MESSAGE);
        }
        return new HomePage(driver);
    }


}
