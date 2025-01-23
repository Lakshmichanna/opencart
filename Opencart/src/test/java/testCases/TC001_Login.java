package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountpage;

public class TC001_Login extends BaseClass{
	
	@Test
	public void verify_login() {
		
		logger.info(" *** Login test case ***");
		
	try {
		
		Homepage hp= new Homepage(driver);
		hp.clickMyacc();
		hp.clicklogin();
		
		Loginpage lp = new Loginpage(driver);
		lp.login(p.getProperty("username"), p.getProperty("password"));
		
		MyAccountpage mp = new MyAccountpage(driver);
		Boolean targetpage = mp.isMyAccHeadingExists();
		
		// here assert will compare actual & expected if not matched then 3rd parameter will print 
		//Assert.assertEquals(targetpage,true,"Login Failed");
		
		Assert.assertTrue(targetpage);
		
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info(" *** Finished Login test case***");
	
	}

}
