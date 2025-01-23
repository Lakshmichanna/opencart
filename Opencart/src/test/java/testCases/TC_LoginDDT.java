package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountpage;
import utilities.DataProviders;

public class TC_LoginDDT extends BaseClass{


	@Test(dataProvider = "LoginData", dataProviderClass =DataProviders.class)
	public void verifyLogin(String email, String password,String exp) {

		logger.info("******* Started TC_LoginDDT  Test ***** ");
		
		try {
		Homepage hp= new Homepage(driver);
		hp.clickMyacc();
		hp.clicklogin();

		Loginpage lp = new Loginpage(driver);
		lp.login(email,password);

		MyAccountpage mp = new MyAccountpage(driver);
		Boolean targetpage = mp.isMyAccHeadingExists();

		/* Data is valid - Login success - Testpassed - logout
		 * 				    Login failed - Testfailed
		 * 
		 * Data is invalid - Login success -Testfailed -logout
		 * 				     Login failed - Testpassed
		 * 
		 * Implementing the above cases in logic below
		 * 
		 */

		if(exp.equalsIgnoreCase("Valid")) {

			if(targetpage==true) {

				mp.clicklogout();
				AssertJUnit.assertTrue(true);
			}
			else {
				AssertJUnit.assertTrue(false);	
			}
		}
		if(exp.equalsIgnoreCase("InValid")) {

			if(targetpage==true) {

				mp.clicklogout();
				AssertJUnit.assertTrue(false);
			}
			else {
				AssertJUnit.assertTrue(true);	
			}
		}
		
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("******* Finished TC_LoginDDT  Test ***** ");
	}



}
