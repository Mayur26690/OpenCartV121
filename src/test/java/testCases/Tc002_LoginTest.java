package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class Tc002_LoginTest extends BaseClass {
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		
		logger.info("----Starting Tc002_LoginTest----");
		try {
		HomePage hp = new HomePage(driver);  //First we need to go to homepage
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		 //We have already created object of Properties class in Base Class. So we can use it directly
		lp.setEmail(p.getProperty("email"));  //This is positive test case so we will pass valid data
		lp.setPassword(p.getProperty("password"));  //We have this in config.properties
		lp.clickLogin();
		
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExist();
		
		Assert.assertEquals(targetPage, true,"Login failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("----Ending Tc002_LoginTest----");
		
	}
}
