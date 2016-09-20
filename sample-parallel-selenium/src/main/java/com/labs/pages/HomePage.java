package com.labs.pages;

import com.labs.modules.Header;
import com.labs.modules.ShoppingCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class HomePage extends AbstractPage{

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "a[test-event='go_to_home']")
    public WebElement coppertoneLogo;

    @FindBy(css = "p[test-assert='go_to_home']")
    public WebElement homePageHeadline;

//    @FindBy(css="[test-event='go_to_products']")
//    public WebElement productsLink;
//
//    private By coppertoneLogo = By.cssSelector("a[test-event='go_to_home']");
//    private By homePageHeadline = By.cssSelector("p[test-assert='go_to_home']");
    private By productsLink = By.cssSelector("[test-event='go_to_products']");

    public WebElement getProductsLink(){
        return driver.findElement(productsLink);
    }
//
//    public void clickCoppertoneLogo(){
//        driver.findElement(coppertoneLogo).click();
//    }
//
//    public WebElement getHomePageHeadline(){
//        return driver.findElement(homePageHeadline);
//    }
//
//    public void clickProductsLink(){
//        driver.findElement(productsLink).click();
//    }

//    public WebElement getProducts
    public void clickProductsLink(){
        //productsLink.click();
    }
}
