package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = ".\\testData\\Book1.xlsx";  //taking xl file form testData folder. This path will be passed to constructor of ExcelUtility class
		
		ExcelUtility xlutil = new ExcelUtility(path);  //Creating object og ExcelUtility class that we created
		
		int totalrows = xlutil.getRowCount("sheet1");
		int totalcols = xlutil.getCellCount("sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols]; //created 2 dimantional array which store all data
		
		for(int i=1;i<totalrows;i++) {
			for(int j=0; j<totalcols; j++) {
				logindata[i-1][j] = xlutil.getCellData("sheet1", i, j);
			}
		}
		
		return logindata;  //returns 2-D array
		
	}
	
}
