package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {  //This parent of all Page Object class. As we will need driver and PageFactory.init method in all classes. 
	WebDriver driver;
	
	public BasePage(WebDriver driver) {  //This is for re-usability
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
