package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class tc001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		
		logger.info("***Starting tc001_AccountRegistrationTest ****");
		try {
		HomePage hp = new HomePage(driver); //We need object of the Homepage object class to access methods. And it has constructor so we need to pass driver
		hp.clickMyAccount();
		logger.info("clicked on MyAccount link");
		hp.clickRegister();
		logger.info("clicked on Register link");  //Basically we can add everyhting that we add in comments
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer info...");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com"); //this test takes same email id only once so we need to create userdefined method
		regpage.setTelephone(randomeNumber());
		regpage.setPassword("xlak323");
		regpage.setConfirmPassword("xlak323");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("Test Failed..");  //to get error log
			logger.debug("Debug logs..");  //to get debug log
			logger.info("in Exception");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e) 
		{
			
			logger.info("in Exception");
			
			Assert.fail();
		}
		
		logger.info("***Ending tc001_AccountRegistrationTest ****");
	}
	

	
}
