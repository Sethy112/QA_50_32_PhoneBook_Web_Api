package ui_tests;

import dto.User;
import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends AppManager {

    @Test
    public void loginPositiveTest() {
        System.out.println("first test");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationForm("login@yoho.com",
                "Password123!");
        loginPage.clickBtnLoginForm();
    }

    @Test
    public void loginPositiveTestWithUser() {
        User user = new User("login@yoho.com",
                "Password123!");
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.typeLoginRegistrationFormWithUser(user);
        loginPage.clickBtnLoginForm();
        Assert.assertTrue(loginPage.isLoggedInDisplayed());
        Assert.assertTrue(loginPage.isBtnAdd());



    }
}
