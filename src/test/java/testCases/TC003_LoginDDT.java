package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid -- Log in sucess -- test pass -- log out
 * Data is valid --- log in fauled --- test fail
 * 
 * Data is invalid -- log in success - test fail  -- log out 
 * Data is invalid -- log in failed 
 */
public class TC003_LoginDDT extends BaseClass{
	@Test (dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups= "Datadriven")  // we need to add this parameter because data provider is not in same class. It is located in different package. 
	public void verify_loginDDT(String email, String pwd, String exp) { //Passin gparameter as we are getting all these field form Excel
		logger.info("Test started");
		//Homepage
		try {
		HomePage hp = new HomePage(driver);  //First we need to go to homepage
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		 //We have already created object of Properties class in Base Class. So we can use it directly
		lp.setEmail(email);  //This is positive test case so we will pass valid data
		lp.setPassword(pwd);  //We have this in config.properties
		lp.clickLogin();
		
		//MyAccount page
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExist();
		//logic 
		if(exp.equalsIgnoreCase("Valid")){  //data is valid
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}else {
				Assert.assertTrue(false);
			}
		}else {  //data is invalid
			if(targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}else {
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e) {
			Assert.fail();
		}
		
		
		logger.info("Test Ended");
		
	}
}
