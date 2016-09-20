package com.labs.common;

import com.labs.pages.AbstractPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class BaseTest {
    protected WebDriver driver;
    protected ArrayList<AbstractPage> pages = new ArrayList<AbstractPage>();

    public void setPages(AbstractPage abstractPage){
        pages.add(abstractPage);
    }

    public ArrayList<AbstractPage> getPages(){
        return pages;
    }

    public void setPageFactory(WebDriver driver, ArrayList<AbstractPage> pages){
        for (AbstractPage currentPage : pages) {

            PageFactory.initElements(driver, currentPage);
        }
    }

    @Before
    public void setUp(){
        //driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        setPageFactory(driver,getPages());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    public boolean isElementExist(By by) throws NoSuchElementException{
        boolean isExist;
        try{
            isExist = driver.findElement(by).isDisplayed();
//                System.out.println("Wait for expected element displayed");
//                System.out.println("Is element displayed : " + driver.findElement(by).isDisplayed());
//                System.out.println("No such element status : " + NoSuchElementException.class.desiredAssertionStatus());
//            }
//            isExist = true;
        }catch(NoSuchElementException e){
            e.printStackTrace();
//            System.out.println("No expected element displayed");
            isExist=false;
        }
        return isExist;
    }

    public void waitForElement(By by) throws Exception{
        //WebElement element = driver.findElement(by);
        delay(1000);
        int count = 0;
        int timeout = 60;
        while(isElementExist(by) || !NoSuchElementException.class.desiredAssertionStatus()){
//            System.out.println("element isDisplayed: " + element.isDisplayed());

            try {
                if(!isElementExist(by) && NoSuchElementException.class.desiredAssertionStatus()){
                    delay(1000);
                    count++;
                    System.out.println("delay for " + count + " secs.");
                    if(count == timeout){
                        System.out.println("timed out");
                        count=0;
                        break;
                    }
                }
                else if(isElementExist(by) && driver.findElement(by).isDisplayed()){
//                    System.out.println("element has been found");
                    count=0;
                    break;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void delay(long timeout) throws InterruptedException {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void isFeedbackPopUpDisplayed() throws InterruptedException {
        delay(500);
        WebElement feedbackPopUp = driver.findElement(By.cssSelector("#metrixlab_darkbox"));
//        if(isElementExist()){
//            delay(1000);
//        }else
        if(feedbackPopUp.isDisplayed()){
            driver.get(driver.getCurrentUrl());
        }
    }

    public void waitForLoadingBarDismissed() throws InterruptedException {
        delay(1000);
        int count=0;
        int timeout=10;
        By loadingBar = By.cssSelector(".loading-indicator-masking");
//        boolean isEnabled = driver.findElement(loadingBar).isEnabled();
        while (!isElementExist(loadingBar)){
            delay(1000);
            count++;
            if(count == timeout){
                break;
            }else if(!isElementExist(loadingBar)){
                break;
            }
        }
    }

    public boolean waitElementDisplayed(){
        boolean isExist;
        try {
            isExist = driver.findElement(By.cssSelector("")).isDisplayed();
        }catch (NoSuchElementException e){
            isExist = false;
        }
        return isExist;
    }
}
