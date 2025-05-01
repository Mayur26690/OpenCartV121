package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	//WebDriver driver;  we don't need here as we are invoking parent class constructor and variable. 
	
	public HomePage(WebDriver driver) { //to invoke parent class constructor we need to create child constructor. 
		super(driver);  //This is inheritance.we can invoke parent class constructor, parent variable and parent class method by using super keyword.   We are passing driver to parent constructor. The Parent class constructor will re
	}
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath ="//a[@title='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(linkText = "Login")
	WebElement linkLogin;
	
	//for every element we need action method
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
}
