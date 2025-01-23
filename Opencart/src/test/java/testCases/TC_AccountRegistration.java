package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.org.slf4j.internal.Logger;

import pageObjects.Homepage;
import pageObjects.Registrationpage;

public class TC_AccountRegistration extends BaseClass {





	@Test
	public  void AccRegistration() {

		logger.info("*** Tc_Account registration is creating  ****");
		try {
			Homepage hp =new Homepage(driver);

			hp.clickMyacc();
			logger.info(" clicked on My account....");
			hp.clickregister();

			Registrationpage rp = new Registrationpage(driver);
			logger.info(" Providing customer details ....");
			rp.personalDetails(randomstring().toUpperCase(), randomstring().toUpperCase(), randomstring(), randomnumber());		
			rp.setPassword(randomalphanumeric());
			rp.register();
			logger.info("*** Validating the expected message... ****");
			String regmsg = rp.getRegistrationMsg();
			if(regmsg.equals("Your Account Has Been Created!"))
			{
				AssertJUnit.assertTrue(true);
			}
			else {
				logger.error(" Test Failed... ");
				logger.debug(" Debug logs");
				AssertJUnit.assertTrue(false);
			}
		}
		catch(Exception e) {

			Assert.fail();
		}

		logger.info("Account Registration is finished....");
	}
}