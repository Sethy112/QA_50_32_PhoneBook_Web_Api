package ui_tests;

import manager.AppManager;
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
        loginPage.fieldEmail();
        loginPage.fieldPassword();
        loginPage.clickBtnLogin1();
        loginPage.clickBtnSignOut();
    }
}
