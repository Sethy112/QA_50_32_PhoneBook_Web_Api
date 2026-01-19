package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@name='email']")
    WebElement fieldEmail;

    public void fieldEmail() {
        fieldEmail.sendKeys("login@yoho.com");
        pausa(2);
    }

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement fieldPassword;

    public void fieldPassword() {
        fieldPassword.sendKeys("Password123!");
        pausa(2);
    }


    @FindBy(xpath = "//button[@name='login']")
    WebElement btnLogin1;

    public void clickBtnLogin1() {
        btnLogin1.click();
        pausa(2);
    }

    @FindBy(xpath = "//button[text()='Sign Out']")
    WebElement signOut;

    public void clickBtnSignOut() {
        signOut.click();
    }


}
