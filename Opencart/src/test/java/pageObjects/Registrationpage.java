package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Registrationpage extends Basepage{

	public Registrationpage(WebDriver driver) {
		super(driver);

	}

	@FindBy(id ="input-firstname")
	WebElement txtfirstname; 

	@FindBy(id="input-lastname")
	WebElement txtlastname; 

	@FindBy(id="input-email")
	WebElement txtemail; 

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtnumber; 

	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpswrd; 


	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtcfrmpswrd; 

	@FindBy(xpath="//input[@name='agree']")
	WebElement chkpolicy; 

	@FindBy(xpath="//input[@value='Continue']")
	WebElement btncontinue; 


	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgsuccess; 



	public void personalDetails(String fname, String lname, String email, String num) {

		
		txtfirstname.sendKeys(fname);
		txtlastname.sendKeys(lname);
		txtemail.sendKeys(email+"@yopmail.com");
		txtnumber.sendKeys(num);
	
	}
	public void setPassword(String pswrd) {
		txtpswrd.sendKeys(pswrd);
		txtcfrmpswrd.sendKeys(pswrd);

	}

	public void register() {
		chkpolicy.click();
		btncontinue.click();

	}

	public String getRegistrationMsg() {
		try {

			return (msgsuccess.getText());

		}
		catch(Exception e) {
			return (e.getMessage());
		}

	}
}
