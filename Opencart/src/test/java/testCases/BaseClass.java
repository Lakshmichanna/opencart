package testCases;
/* To acheive the resuability of methods we use the base class for all testcase classes */

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
public	WebDriver driver;
	
public Logger logger;//log4j

public Properties p;

	@BeforeClass
	@Parameters({"OS","Browser"})
	public void setup(String os,String browser) throws IOException {

		//Loading properties file 
		
		FileReader f =new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(f);
		
		// Loading the log4j file 
		logger = LogManager.getLogger(this.getClass());
		
		
		switch(browser.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); 
		break;
		case "edge" : driver =new EdgeDriver(); 
		break;
		case "safari" : driver = new SafariDriver(); break;
		default:  System.out.println("Invalid Browser name.... "); return;
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("*** Chrome browser is Opening the application ****");
		driver.get(p.getProperty("appURL")); // Reading the data from properties file
		driver.manage().window().maximize();


	}
	@AfterClass
	public void teardown() {
		logger.info("*** Chrome browser is Closed ****");
		driver.quit();
	}

	public String randomstring() {
		String dynamicstring = RandomStringUtils.randomAlphabetic(6);
		
		return dynamicstring;

	}
	public String randomnumber() {
		String dynamicnumber = RandomStringUtils.randomNumeric(10);
		return dynamicnumber;
	}
	public String randomalphanumeric() {
		String dynamicstring = RandomStringUtils.randomAlphabetic(5);
		String dynamicnumber = RandomStringUtils.randomNumeric(3);

		return (dynamicstring+"@"+ dynamicnumber);
	}
}
