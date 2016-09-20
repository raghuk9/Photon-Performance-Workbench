package com.sample.one;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by maretska on 3/31/16.
 */
public class ParallelTest {
    private WebDriver driver = null;
    private JavascriptExecutor jse;
    private Dimension dimension;
    private DesiredCapabilities caps;

    @BeforeTest(alwaysRun=true)
    @Parameters({"platform","browser", "url"})
    public void setup(String platform, String browser, String url) throws MalformedURLException, InterruptedException
    {
        caps = new DesiredCapabilities();

        //Platforms
        if(platform.equalsIgnoreCase("Windows")) {
            caps.setPlatform(Platform.WINDOWS);
        }

        if(platform.equalsIgnoreCase("MAC")) {
            caps.setPlatform(Platform.MAC);
        }

        //Browsers
        if(browser.equalsIgnoreCase("Chrome")) {
            caps = DesiredCapabilities.chrome();
        }

        if(browser.equalsIgnoreCase("Firefox")) {
            caps = DesiredCapabilities.firefox();
        }

        if(browser.equalsIgnoreCase("IE")){
            caps = DesiredCapabilities.internetExplorer();
        }
        if(browser.equalsIgnoreCase("safari")){
            caps = DesiredCapabilities.safari();
        }

        driver = new RemoteWebDriver(new URL(Constant.HUB_URL + "/wd/hub"), caps);
        //driver = new FirefoxDriver(caps);
        dimension = new Dimension(480,960);
        driver.manage().window().setSize(dimension);

        jse = (JavascriptExecutor)driver;

        //Launch URL
        long id = Thread.currentThread().getId();
        //System.out.println("Thread id is: " + id);
        driver.get(url);

    }

    @Test
    public void test() throws InterruptedException {

        System.out.println(driver.getCurrentUrl());
        Thread.sleep(15000);
        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");
        waitAndClick(By.cssSelector("a.clickhere"));

        /** Product Detail **/
        Thread.sleep(15000);
        waitAndClick(By.cssSelector("div[test-event='select_categories{Regular Strength Aspirin}']"));
        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");
        System.out.println(driver.getCurrentUrl());
        Thread.sleep(15000);
        waitAndClick(By.cssSelector("img[test-event='select_product{Genuine Bayer Aspirin}']"));

        /** Add To Cart **/
        Thread.sleep(15000);
        wait("img[test-assert='gba_page_main_image']");
        System.out.println(driver.getCurrentUrl());
        waitAndClick(By.cssSelector("select[test-input='add_to_cart_success']"));
        waitAndClick(By.cssSelector("option[value='2']"));
        waitAndClick(By.cssSelector("div[test-event='add_to_cart']"));
        Thread.sleep(15000);
    }

    public void wait(String selector) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(selector)));
        WebElement element = driver.findElement(By.cssSelector(selector));
        Assert.assertTrue(element.isDisplayed()||element.isEnabled());
    }

    public void openUrl(String url){
        driver.get(url);
    }

    public void waitAndClick(By by) throws InterruptedException{
        WebElement element = driver.findElement(by);

        if (element.isDisplayed() || element.isEnabled() ) {
            String bayerSpinner = "div.add-to-cart-masking.banana";
            String lumaHeader = "body > header > div.header-logo > a > img";
//            WebElement spinner = driver.findElement(By.cssSelector(bayerSpinner));
//            int timeout = 0;
//            while(spinner.isDisplayed() || !element.isDisplayed()){
//                try{
//                    System.out.println("Spinner displayed, wait until spinner dismissed.");
//                    Thread.sleep(1000);
//                    timeout++;
//                    if (!spinner.isDisplayed()){
//                        System.out.println("Page has been loaded, now click the expected element.");
//                        break;
//                    }else if(timeout==60){
//                        System.out.println("wait timed out");
//                        break;
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
            Thread.sleep(5000);
            element.click();
        }

    }

    List<User> users = new ArrayList<User>();

    User user1 = new User("user1@mailinator.com","user123");
    User user2 = new User("user2@mailinator.com","user213");
    User user3 = new User("user3@mailinator.com","user321");
    User user4 = new User("user4@mailinator.com","user231");
    User user5 = new User("user5@mailinator.com","user312");
    User user6 = new User("user6@mailinator.com","user132");

    public List<User> getUsers(){
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

        return users;
    }
    public void inputValue(By by, String value){
        driver.findElement(by).sendKeys(value);
    }

    public int getRandomIndex(){
        Random randomNumber = new Random();
        int minimum = 0;
        int range = getUsers().size() - minimum;
        int randomNum = randomNumber.nextInt(range) + minimum;

        return randomNum;
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
