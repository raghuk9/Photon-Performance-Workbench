package com.sample.one;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class JunitTest2 extends Browsers {
	WebDriver driver;
	
	public JunitTest2(String Browsers) throws MalformedURLException{
		
	}
	
	@Test(threadPoolSize=3, invocationCount=5, timeOut=10000)
	public void partTest(){
		System.out.print("haha");

	}

}
