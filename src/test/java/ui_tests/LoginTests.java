package ui_tests;

import manager.AppManager;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

public class LoginTests extends AppManager {





    @Test
    public void loginPositiveTest() {
        System.out.println("first test");
        HomePage homePage = new HomePage(getDriver());


        homePage.clickBtnLogin();

        homePage.fieldEmail();

        homePage.fieldPassword();
        pausa(2);

        homePage.clickBtnLogin1();
        pausa(2);

        homePage.clickBtnSignOut();
        pausa(2);


    }

    public void pausa(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
