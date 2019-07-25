package com.crm.basePack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import com.crm.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestBase {//Parent Class
	
		public static WebDriver driver;  //static is for easy availablity throughout the project
	
		public static Properties prop;
	 
		public static ExtentReports extent;
		public static ExtentTest logger;
		
		public static DesiredCapabilities desiredCapabilities;
		
		
		
		
		
		public static void setCapabilities() throws Exception {
			desiredCapabilities= new DesiredCapabilities();
			
			
			desiredCapabilities.setCapability("deviceName", "Honor 9");
			desiredCapabilities.setCapability("udid", "S6L3Y18404004737");
			desiredCapabilities.setCapability("platformName", "Android");
			desiredCapabilities.setCapability("platformVersion", "8.0");
			
			desiredCapabilities.setCapability("appPackage", "com.android.chrome");
			desiredCapabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
			
			URL url=new URL("http://127.0.0.1:4723/wd/hub");
			driver=new RemoteWebDriver(url, desiredCapabilities);
			
			driver.get(prop.getProperty("url"));
			
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
		}
	
	 public TestBase(){
		
		prop=new Properties();
		try {
			FileInputStream ip= new FileInputStream("/Users/jishnu/Documents/MyFramework/MyProject/src/main/resources/com/crm/config/config.properties");
			prop.load(ip);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialaizationMethod(String browser) {
		
		//String browser=prop.getProperty("browser"); It can be used Instead of @Parameter....Both are setting the environment variables
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/jishnu/Downloads/chromedriver");
			driver= new ChromeDriver();
			
		}else if(browser.equals("firefox"))
		{
			////
		}
		System.out.println(driver);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		
		
		
		
	}
	
	public static void extendReportSetUp(String testName,String testCaseDescription) throws IOException {
		 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/CRM_TestReports.html", true);
		 extent
         .addSystemInfo("Host Name", "SoftwareTestingMaterial")
         .addSystemInfo("Environment", "Automation Testing")
         .addSystemInfo("User Name", "Jishnu Mahesh");
         extent.loadConfig(new File("/Users/jishnu/Documents/MyFramework/MyProject/extent-config.xml"));
         
         logger = extent.startTest(testName);
 		
 		
 		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
 		FileUtils. copyFile(src, new File("/Users/jishnu/Documents/SCREENSHOTS/pic.png"));
 		//Users/jishnu/Documents/SCREENSHOTS/pic.png"
 		 logger.log(LogStatus.PASS, logger.addScreenCapture(System.getProperty("user.dir") +"/test-output/ScreenshotsCRM_Test.png")+testCaseDescription);
 		 extent.endTest(logger);
 		 extent.flush();
 		 extent.close();
		
	}
	
	
	
	
	

}
