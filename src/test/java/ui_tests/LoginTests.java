package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;
import utils.RetryAnalyser;

import java.lang.reflect.Method;

import static utils.PropertiesReader.*;

public class LoginTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToLoginPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());
    }


    @Test(retryAnalyzer = RetryAnalyser.class)
    public void loginPositiveTest() {
//        loginPage.typeLoginRegistrationForm("login@yoho.com","Password123!");

        loginPage.typeLoginRegistrationForm(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnAddPresent("ADD"));
    }

    @Test
    public void loginPositiveTestWithUser(Method method) {
        User user = new User(getProperty("base.properties", "login"),
                getProperty("base.properties", "password"));
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnLogin();
//        LoginPage loginPage = new LoginPage(getDriver());
        logger.info("start test "+ method.getName()+ " with user "+ user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
//        Assert.assertTrue(loginPage.isLoggedInDisplayed());
//        Assert.assertTrue(loginPage.isBtnAdd());
        Assert.assertTrue(new ContactPage(getDriver()).isTextInBtnSignOutPresent("Sign Out"));
    }

    @Test
    public void loginNegativeTest_WrongEmail() {
        User user = new User("loginyoho.com", getProperty("base.properties", "password"));
//        HomePage homePage = new HomePage(getDriver());
//        homePage.clickBtnLogin();
//        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }



    @Test
    public void loginNefativeTestisBlank() {
        User user = new User(" ", " ");
//        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }


    @Test
    public void loginNegativeTestWithTWoDots() {
        User user = new User("login@yoho..com", getProperty("base.properties", "password"));
//        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTestWithTwoAtS() {
        User user = new User("login@@yoho.com", getProperty("base.properties", "password"));
//        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void loginNegativeTestPasswordIsBlank() {
        User user = new User("login@@yoho.com", "");
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
    @Test
    public void loginNegativeTestEmailIsBlank() {
        User user = new User("", getProperty("base.properties", "password"));
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
    @Test
    public void loginNegativeTestEmailisTruPasswordWrong() {
        User user = new User("login@@yoho.com", getProperty("base.properties", "password"));
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
    @Test
    public void loginNegativeTestPasswordisTruEmailWrong() {
        User user = new User("login@gmail.com", getProperty("base.properties", "password"));
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
}
