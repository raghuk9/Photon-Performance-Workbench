package com.sample.one;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Sel_Grid {
	
	WebDriver driver = null;
	 
  @BeforeTest(alwaysRun=true)
  @Parameters({"platform","browser","version", "url"})
  public void setup(String platform, String browser, String version, String url) throws MalformedURLException
  {  
	  DesiredCapabilities caps = new DesiredCapabilities();
	  
	  //Platforms
	  if(platform.equalsIgnoreCase("Windows"))
	  caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);

	  if(platform.equalsIgnoreCase("MAC"))
	  caps.setPlatform(org.openqa.selenium.Platform.MAC);
	  
	  //Browsers
	  if(browser.equalsIgnoreCase("Chrome"))
	  caps = DesiredCapabilities.chrome();

	  if(browser.equalsIgnoreCase("Firefox"))
	  caps = DesiredCapabilities.firefox();
	  
	  //Version
	  caps.setVersion(version);

	  driver = new RemoteWebDriver(new URL("http://52.92.259.7:4444/wd/hub"), caps);
	  
	  //Launch URL
	  driver.get(url);
  }

  @Test
  public void test() {
	  System.out.println("Test function");
  }
  
  @AfterTest
  public void teardown() {
	  driver.quit();
  }

}
