package com.paralleltest;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by Irfan Mauludin on 4/18/16.
 */

@RunWith(Parallelized.class)
public class JUnitParallelTest {
    private String platform;
    private String browserName;
    private String browserVersion;

    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception{
        LinkedList<String []> env = new LinkedList<String[]>();
        //env.add(new String[]{Platform.WINDOWS.toString(),"chrome","27"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        env.add(new String[]{Platform.LINUX.toString(),"firefox","20"});
        //env.add(new String[]{Platform.WINDOWS.toString(),"ie","11"});
        //env.add(new String[]{Platform.WINDOWS.toString(),"opera","12.14"});

        return env;
    }

    public JUnitParallelTest(String platform, String browserName, String browserVersion){
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    private WebDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform",platform);
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("browserVersion",browserVersion);
        capabilities.setCapability("build","JUnit-Parallel");

        driver = new RemoteWebDriver(
                new URL("http://50.19.207.2:4444/wd/hub"),capabilities
        );
    }

    @Test
    public void testSimple() throws Exception {
        driver.get("http://www.google.com");
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("BrowserStack");
        element.submit();
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("Screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }


}
