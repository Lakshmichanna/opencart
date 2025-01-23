package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage{

	public Loginpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

@FindBy(xpath = "//input[@id='input-email']")
WebElement txtemail ;
	

@FindBy(xpath = "//input[@id='input-password']")
WebElement txtpassword;
	
@FindBy(xpath = "//input[@value='Login']")
WebElement loginbtn;
	
@FindBy(xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
WebElement forgotpwrd;
	

public void login(String usermail, String password) {
	
	txtemail.sendKeys(usermail);
	txtpassword.sendKeys(password);
	loginbtn.click();
}




}
