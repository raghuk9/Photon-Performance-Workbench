package com.labs.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Irfan Mauludin on 5/17/16.
 */
public class AddToCartPopUp extends AbstractModule{

    public AddToCartPopUp(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    protected By addToCartSuccess = By.cssSelector("[test-assert='add_to_cart_success']");
    protected By goToCartBtn = By.cssSelector("[test-event='go_to_cart_from_overlay']");
    protected By continueShoppingBtn = By.cssSelector("[test-event='continue_shopping_atc']");

    public WebElement getAddToCartSuccess(){
        return driver.findElement(addToCartSuccess);
    }

    public WebElement getGoToCartBtn(){
        return driver.findElement(goToCartBtn);
    }

    public By getGoToCartBtnSelector(){
        return goToCartBtn;
    }

    public By getAddToCartSuccessSelector(){
        return addToCartSuccess;
    }

    public WebElement getContinueShoppingBtn(){
        return driver.findElement(continueShoppingBtn);
    }
}
