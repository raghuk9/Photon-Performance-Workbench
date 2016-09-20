package com.sample.one;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Browsers {
	WebDriver driver;
	
	public static WebDriver getDefaultDriver(String seleniumServer) throws MalformedURLException {
		return getFirefoxDriver(seleniumServer);
	}
	
	public static WebDriver getDriver(String seleniumServer, String driverName, ArrayList<String> resolution) throws MalformedURLException{
		WebDriver driver;
		if(driverName.equalsIgnoreCase("Firefox")){
			driver = getFirefoxDriver(seleniumServer);
		}else if(driverName.equalsIgnoreCase("chrome")){
			driver = getChromeDriver(seleniumServer);
		}else{
			driver = getPhantomJS(seleniumServer);
		}
		driver.manage().window().setSize(new Dimension(Integer.parseInt(resolution.get(0)), Integer.parseInt(resolution.get(1))));
		return driver;
	}
	
	public static WebDriver getFirefoxDriver(String seleniumServer) throws MalformedURLException {
		// Create an instance of WebDriver backed by Firefox
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.cache.disk.enable", false);
		profile.setPreference("security.fileuri.strict_origin_policy", false);
		profile.setPreference("browser.startup.homepage", "about:blank");
		profile.setPreference("startup.homepage_welcome_url", "about:blank");
		profile.setPreference("startup.homepage_welcome_url.additional", "about:blank");
		WebDriver driver;
		if(seleniumServer.equalsIgnoreCase("")){
			// use local driver
			driver = new FirefoxDriver(profile);
		}else{
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			System.out.println("get firefox");
			dc.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new RemoteWebDriver(new URL(seleniumServer), dc);
		}
		driver.manage().timeouts().setScriptTimeout(1, TimeUnit.HOURS);		
		return driver;
	}

	public static WebDriver getSafariDriver(String seleniumServer) {
		System.setProperty("webdriver.safari.noinstall", "true");
		WebDriver driver = new SafariDriver();
		driver.manage().timeouts().setScriptTimeout(1, TimeUnit.HOURS);
		return driver;
	}
	
	public static WebDriver getChromeDriver(String seleniumServer) throws MalformedURLException {
		WebDriver driver;
		System.out.println("get chrome");
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL(seleniumServer), dc);
		driver.manage().timeouts().setScriptTimeout(1, TimeUnit.HOURS);
		return driver;
	}
	
	public static WebDriver getPhantomJS(String seleniumServer) throws MalformedURLException {
        System.out.println("using phantom");
		DesiredCapabilities caps = DesiredCapabilities.phantomjs();
        WebDriver driver = new RemoteWebDriver(new URL(seleniumServer), caps);
        driver.manage().timeouts().setScriptTimeout(1, TimeUnit.HOURS);
        return driver;

    }

}
