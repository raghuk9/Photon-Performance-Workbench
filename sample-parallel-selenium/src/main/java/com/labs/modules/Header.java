package com.labs.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Irfan Mauludin on 5/17/16.
 */
public class Header extends AbstractModule{

    public Header(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private By shoppingCartlink = By.cssSelector("[test-event='go_to_cart_from_link']");

    public WebElement getShoppingCartLink(){
        return driver.findElement(shoppingCartlink);
    }
}
