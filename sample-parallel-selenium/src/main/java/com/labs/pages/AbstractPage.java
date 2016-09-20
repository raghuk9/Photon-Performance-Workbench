package com.labs.pages;

import com.labs.common.BaseTest;
import com.labs.modules.Header;
import com.labs.modules.ShoppingCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class AbstractPage{

    protected WebDriver driver;
    protected Header header;
    protected ShoppingCart shoppingCart;

    public Header getHeader() {
        header = new Header(driver);
        return header;
    }

    public ShoppingCart getShoppingCart(){
        shoppingCart = new ShoppingCart(driver);
        return shoppingCart;
    }

    //    public WebDriver getDriver(){
//        System.setProperty("webdriver.chrome.driver","src/main/resources/driver/chromedriver/chromedriver");
//        return new ChromeDriver();
//    }



//    protected By productsLink = By.cssSelector("span[test-event='go_to_products']");
    protected By shoppingCartLink = By.cssSelector("div[test-event='go_to_cart_from_link']");

//    public void clickProductsLink(){
//        driver.findElement(productsLink).click();
//    }

    public void clickShoppingCartLink(){
        driver.findElement(shoppingCartLink).click();
    }

    public WebElement getShoppingCartLink(){
        return driver.findElement(shoppingCartLink);
    }
}
