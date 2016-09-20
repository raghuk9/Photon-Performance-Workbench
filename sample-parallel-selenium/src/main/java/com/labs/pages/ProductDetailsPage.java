package com.labs.pages;

import com.labs.modules.AbstractModule;
import com.labs.modules.AddToCartPopUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class ProductDetailsPage extends AbstractPage {
    public AddToCartPopUp addToCartPopUp;
    private By productTitle;

    public void setProductTitle (String productName, String productCategory){
        productTitle = By.cssSelector("h1[test-assert='go_to_product_from_side_link{"+productName+"},select_categories{"+productCategory.toUpperCase()+"}']");
    }

    public By getProductTitleSelector(){
        return productTitle;
    }

    public ProductDetailsPage(WebDriver driver){
        this.driver = driver;
        addToCartPopUp = new AddToCartPopUp(driver);
        PageFactory.initElements(driver,this);
    }

//    public AbstractModule getAddToCartPopUp(){
//        addToCartPopUp = new AddToCartPopUp(driver);
//        return addToCartPopUp;
//    }

    protected By addToCartBtn = By.cssSelector("div.button-content-cart[test-event='add_to_cart_success,add_to_cart_error']");

    public void clickSideProduct(String productName){
        driver.findElement(By.cssSelector("a[test-event='go_to_product_from_side_link{"+productName+"}']"));
    }

    public WebElement getProductTitle(){
        return driver.findElement(getProductTitleSelector());
    }

    public void clickAddToCartBtn(){
        driver.findElement(addToCartBtn).click();
    }
}
