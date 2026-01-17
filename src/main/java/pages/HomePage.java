package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get("https://telranedu.web.app/home");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,10),this);


    }

@FindBy(xpath = "//a[text()='LOGIN']")
    WebElement btnLogin;


    public  void clickBtnLogin(){
        btnLogin.click();
    }
    @FindBy(xpath = "//input[@name='email']")
    WebElement fieldEmail;

    public void fieldEmail(){
        fieldEmail.sendKeys("login@yoho.com");
    }

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement fieldPassword;

    public void fieldPassword(){
        fieldPassword.sendKeys("Password123!");
    }
    @FindBy(xpath = "//button[@name='login']")
    WebElement  btnLogin1;

    public void clickBtnLogin1(){
        btnLogin1.click();
    }

    @FindBy(xpath ="//button[text()='Sign Out']" )
    WebElement signOut;

    public void clickBtnSignOut(){
        signOut.click();
    }

}
