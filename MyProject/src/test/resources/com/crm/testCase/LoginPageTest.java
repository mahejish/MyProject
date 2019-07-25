package com.crm.testCase;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.basePack.TestBase;
import com.crm.pages.HomePage;
import com.crm.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class LoginPageTest extends TestBase{
	
	 ExtentReports extent;
	 ExtentTest logger;
	
	LoginPage loginPage;
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws Exception {
		
		//setCapabilities();
		initialaizationMethod(browser);
		loginPage=new LoginPage();
		
	}
	 public void getResult(ITestResult result){
		 if(result.getStatus() == ITestResult.FAILURE){
		 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
		 logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 }else if(result.getStatus() == ITestResult.SKIP){
		 logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }
	 }
	@Test(priority=1,enabled=true)
	public void loginPageTittleTest() throws IOException {
		 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/CRM_TestReports.html", true);
		 extent
         .addSystemInfo("Host Name", "SoftwareTestingMaterial")
         .addSystemInfo("Environment", "Automation Testing")
         .addSystemInfo("User Name", "Jishnu Mahesh");
         extent.loadConfig(new File("/Users/jishnu/Documents/MyFramework/MyProject/extent-config.xml"));

         try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         String tittile=loginPage.validateLoginPageTittle();
         
         SoftAssert assert1= new  SoftAssert();
         
         assert1.assertEquals(tittile, "Zoho Accounts");
         
         //assert1.assertAll();
		
        logger = extent.startTest("ZahoTittleTest");
		
		
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils. copyFile(src, new File("/Users/jishnu/Documents/SCREENSHOTS/pic.png"));
		
		 logger.log(LogStatus.PASS, logger.addScreenCapture("/Users/jishnu/Documents/SCREENSHOTS/pic.png")+"Test Case Passed is passTest");
		 extent.endTest(logger);
		 extent.flush();
		 extent.close();

	}
	@Test(priority=2,enabled=false)
	public void crmLogoTest() {
		boolean flag=loginPage.validatecrmLogo();
		Assert.assertEquals(flag,true);
	}
	@Test(priority=3,enabled=false)
	public void loginTest() {
		HomePage homePage=loginPage.validateLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
