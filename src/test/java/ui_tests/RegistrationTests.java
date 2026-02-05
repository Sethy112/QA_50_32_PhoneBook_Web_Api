package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactPage;
import pages.HomePage;
import pages.LoginPage;

import static utils.PropertiesReader.getProperty;
import static utils.UserFactory.*;

import java.util.Random;

public class RegistrationTests extends AppManager {
    LoginPage loginPage;

    @BeforeMethod
    public void goToRegistrationPage() {
        new HomePage(getDriver()).clickBtnLogin();
        loginPage = new LoginPage(getDriver());

    }

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        User user = new User("muyir" + i + "@co.il",
                getProperty("base.properties", "password"));
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver()).
                isTextInContactPageMassagePresent("No Contacts here!"));

    }

    @Test
    public void registrationPositiveTest_WithFaker() {
        User user = positiveUser();
        System.out.println(user);
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(new ContactPage(getDriver()).
                isTextInContactPageMassagePresent("No Contacts here!"));

    }

    @Test
    public void regictrationNefativeTestisBlank() {
        User user = new User(" ", " ");
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }

    @Test
    public void registrationNegativeTestWithTwoDots() {
        User user = new User("login@yoho..com", getProperty("base.properties", "password1"));
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
//        Assert.assertTrue(loginPage.
//                isTextPresentCode401("401"));
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
    @Test
    public void registrationNegativeTestWithTwoAtS() {
        User user = new User("login@@yoho.com", getProperty("base.properties", "password"));
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertEquals(loginPage.closeAlertReturnText(),
                "Wrong email or password");
    }
    @Test
    public void registrationNegativeTest_WithFaker_EmptyPassword() {
        User user = positiveUser();
        user.setPassword("");

        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnRegistrationForm();
        Assert.assertTrue(loginPage.closeAlertReturnText()
                .contains("Wrong email or password"));
    }
}
