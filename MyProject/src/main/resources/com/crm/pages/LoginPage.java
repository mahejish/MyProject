package com.crm.pages;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.basePack.TestBase;


public class LoginPage extends TestBase{
	
	@FindBy(name="lid")  //Customize Xpath
	WebElement UserName;
	
	@FindBy(name="pwd")
	WebElement password;
	
	@FindBy(xpath="//div[@id='signin_submit']")
	WebElement loginBTN;
	
	@FindBy(xpath="//a[@href='javascript:register()']")
	WebElement signUpLnk;

	@FindBy(xpath="//div[@id='signin_submit']")
	WebElement crmLogo;
	
	public LoginPage() {//constructer
		
		PageFactory.initElements(driver, this);
		
	}
	
	public String validateLoginPageTittle() {
		signUpLnk.click();
		
		return driver.getTitle(); 
	}
	
	public boolean validatecrmLogo() {
		
		assertTrue(crmLogo.isDisplayed());
		
		
		/*String path="/Users/jishnu/Documents/SCREENSHOTS/captcha.png";
		File src= crmLogo.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ITesseract image= new Tesseract();
		String imageText="";
		try {
			imageText=image.doOCR(new File(path));
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(imageText);*/
		return crmLogo.isDisplayed();
	}
	
	public HomePage validateLogin(String UserId,String Psswd) {
		UserName.sendKeys(UserId);
		password.sendKeys(Psswd);
		loginBTN.click();
		
		return new HomePage(); // It should return the home page class object if its Logged in.
	}
	
	
}
