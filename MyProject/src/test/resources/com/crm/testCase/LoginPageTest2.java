package com.crm.testCase;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.basePack.TestBase;

public class LoginPageTest2 extends TestBase{

	
	@DataProvider(name="Testing1")
	public Object[][] dataDriver(){
		Object[][] drive= {{"Jishnu",21,47},
						   {"Chirag",23,49},
						   {"Nandu",25,48}};
		
		return drive;
	}
	
	
	
	
	@Test(dataProvider="Testing1")
	public void loginPageTittleTest(String name,int age,int mark){
		
		
		
		System.out.println(name +age+ mark);
		
	}

}
