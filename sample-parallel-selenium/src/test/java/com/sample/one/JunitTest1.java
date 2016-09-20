package com.sample.one;

import java.net.MalformedURLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class JunitTest1 extends Browsers{
	WebDriver driver;
	String hub="http://52.91.159.7:4444/wd/hub";
	String bayerWebsite="https://54.225.139.186:8086";

	@DataProvider(name = "credentials", parallel = false)
	public Object[][] credentials() {
		return new Object[][] {{"chrome","jessica.p","Welcome123"}};
	}

	@Test(dataProvider = "credentials" )
	public void partTest(String browser, String username, String password) throws MalformedURLException, InterruptedException {
		long id = Thread.currentThread().getId();
		System.out.println("Thread id is: " + id);

		ArrayList<String> dimension = new ArrayList<String>();
		dimension.add("400");
		dimension.add("667");
		this.driver = getDriver(hub, browser, dimension);
		driver.get(bayerWebsite);
		System.out.println(driver.getCurrentUrl());

		/** Login **/
		driver.findElement(By.cssSelector("input[test-input-field='username']")).sendKeys(username);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Login']")).click();
		Thread.sleep(10000);


		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");
		explicitWait(By.cssSelector("a.clickhere"));
		System.out.println(driver.getCurrentUrl());

		/** Product Detail **/
		explicitWait(By.cssSelector("div[test-event='select_categories{Regular Strength Aspirin}']"));
		jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");
		System.out.println(driver.getCurrentUrl());
		explicitWait(By.cssSelector("img[test-event='select_product{Genuine Bayer Aspirin}']"));

		/** Add Too Cart **/
		wait("img[test-assert='gba_page_main_image']");
		System.out.println(driver.getCurrentUrl());
		driver.findElement(By.cssSelector("p[test-input='add_to_cart,select_value_qty']")).click();
		driver.findElement(By.cssSelector("li[test-event='select_value_qty1']")).click();
		driver.findElement(By.cssSelector("div.button-content-cart")).click();

	}

	public void wait(String args) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(args)));
	}

	public void explicitWait(By by) throws InterruptedException{
		WebElement element = driver.findElement(by);
	if (element.isDisplayed() || element.isEnabled() ) {
		WebElement spinner = driver.findElement(By.cssSelector("div.add-to-cart-masking.banana"));
		if (spinner.isDisplayed()) {
			System.out.println("spinner display");
			Thread.sleep(10000);
		} else {
			System.out.println("Element found");
		}
		element.click();
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
