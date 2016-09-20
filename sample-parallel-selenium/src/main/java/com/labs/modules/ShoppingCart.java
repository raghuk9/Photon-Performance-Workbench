package com.labs.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Irfan Mauludin on 5/17/16.
 */
public class ShoppingCart extends AbstractModule {

    public ShoppingCart(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private By shoppingCartTitle = By.cssSelector("[test-assert='go_to_cart_from_link,go_to_cart_from_overlay']");

    public WebElement getShoppingCartTitle(){
        return driver.findElement(shoppingCartTitle);
    }
    public By getShoppingCartTitleSelector(){
        return shoppingCartTitle;
    }
}
