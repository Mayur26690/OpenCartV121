package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;  //to create report and set UI of the report
	public ExtentReports extent;   //To set comoon info like browser name, user name etc
	public ExtentTest test;
	
	String repName;
	
	
	public void onStart(ITestContext testContext) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt = new Date();  // to create date
			String timeStamp = df.format(dt);  //it will return date in String format
			//When you combine all 3 statement
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			
			repName = "Test-Report-" + timeStamp + ".html";
			
			sparkReporter = new ExtentSparkReporter(".\\reports\\" +repName); //specify location of the report
			sparkReporter.config().setDocumentTitle("Opencart Automation Report");  //Title of the report
			sparkReporter.config().setReportName("Opencart Functional Testing");  //name of the report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Opencart");
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Env", "QA");
			
			//setting values dynamically
			String os = testContext.getCurrentXmlTest().getParameter("os");  //testCOntext is getting passed as parameter. It tells which test is getting executed. 
			//.getCurrentXmlTest() will get parameter value form xml file
			extent.setSystemInfo("Operating System", os);
			
			String browser = testContext.getCurrentXmlTest().getParameter("browser");  //testCOntext is getting passed as parameter. It tells which test is getting executed. 
			extent.setSystemInfo("Browser", browser);
		
			List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includedGroups.toString());
	}
	}
	
			 public void onTestSuccess(ITestResult result) {
				 test = extent.createTest(result.getTestClass().getName());  //creating new entry and getting which class we are executing and get name
				 test.assignCategory(result.getMethod().getGroups());  //to display groups in report
				 test.log(Status.PASS, result.getName() + " got successfully executed"); //result.getName() will return class name
				 
				 
				  }
			 
			 public void onTestFailure(ITestResult result) {
				 test = extent.createTest(result.getTestClass().getName());  //creating new entry and getting which class we are executing and get name
				 test.assignCategory(result.getMethod().getGroups());  //to display groups in report
				 
				 test.log(Status.FAIL, result.getName()+" got failed");
				 test.log(Status.INFO, result.getThrowable().getMessage());
				 
				 try {
					 String imgPath = new BaseClass().captureScreen(result.getName()); //getting name of the method and passing to capture Screenmethod
					 test.addScreenCaptureFromPath(imgPath);
				 }catch(IOException e1) {
					 e1.printStackTrace();
				 }
				  }
			 
	
	 
		 
		  public void onTestSkipped(ITestResult result) {
			  test = extent.createTest(result.getTestClass().getName());  //creating new entry and getting which class we are executing and get name
				 test.assignCategory(result.getMethod().getGroups());  //to display groups in report
				 test.log(Status.SKIP, result.getName() + " got skipped"); //result.getName() will return class name
				 test.log(Status.SKIP, result.getThrowable().getMessage());
				 
		  }
		 
		
		
		  public void onFinish(ITestContext context) {
		    extent.flush();  //it consolidate everything and generate report
		     //To open report automatically
		    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		    File extentReport = new File(pathOfExtentReport);
		    try {
		    	Desktop.getDesktop().browse(extentReport.toURI());  // To open report in browser
		    	
		    }catch(IOException e) {
		    	e.printStackTrace();
		    
		    //to send email automatically ... We also need to add commons email dependency
		  /*
		    	try{
		     URL url = new URL("file:///"+System.getProperty("user.dir") + "\\reports\\" + repName);
		     //Create the email 
		     ImageHtmlEmail email = new ImageHtmlEmail();
		     email.setDataSourceResolver(new DataSourceUrlResolver(url));
		     email.setHostName("smtp.googlemail.com");
		     email.setSmtpPort(465);
		     email.setAuthenticator(new DefaultAuthenticator("sham@ccl.org","password"));
		     email.setSSLOnConnect(true);
		     email.setFrom("as@gmail.com"); //sender
		     email.setSubject("TEst Report");
		     email.setMsg("see reprot");
		     email.addTo("EmailID");
		     email.attach(url,"extent report", "please check report....");
		     email.send(); 
		     }catch(Exception e1) {
		    	 e1.printStackTrace();
		      
		    }
		     */
		  }
		  }}








	

