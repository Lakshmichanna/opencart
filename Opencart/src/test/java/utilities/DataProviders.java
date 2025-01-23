package utilities;

import org.testng.annotations.DataProvider;


public class DataProviders {

	

		@DataProvider(name ="LoginData")
		public String[][] logindata() throws Exception{
			
			String path = "/Users/thinuser/Cucumberjava/Opencart/testData/Cartlogin.xls";
			String sheet = "Sheet1";
			ExcelUtility ut = new ExcelUtility(path);
			
			return ut.getxlsdata(sheet);
			
			
			
	}
		
		public String[][] xlsdata() throws Exception{
			
			ExcelWB ex = new ExcelWB();
			
			
			String sheet= "Sheet1";
			String path= "/Users/thinuser/Cucumberjava/Opencart/testData/Cartlogin.xls";
			ex.getcontent(path, sheet);
			
			
			return null;
			
			
			
			
		}

}
