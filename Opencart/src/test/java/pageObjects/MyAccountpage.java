package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountpage extends Basepage{

	public MyAccountpage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath ="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout;


	@FindBy(xpath ="//h2[normalize-space()='My Account']")	
	WebElement myaccheading;

	public boolean isMyAccHeadingExists() {
		try {

			return(myaccheading.isDisplayed());

		}
		catch(Exception e ){
			return false;
		}
	}

	public void clicklogout() {

		logout.click();
	}

}
