package com.labs.pages;

import com.labs.modules.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class ProductCategoryPage extends AbstractPage {

    public ProductCategoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

//    @FindBy(css="h1[test-assert='go_to_products,continue_shopping_atc,continue_shopping_sc']")
//    public WebElement productCategoryHeadline;

    @FindBy(css="img[test-event='select_categories{SPORT}']")
    public WebElement sportCategoryImg;

    public By productCategoryHeadline = By.cssSelector("h1[test-assert='go_to_products,continue_shopping_atc,continue_shopping_sc']");
    private By sportCategory = By.cssSelector("img[test-event='select_categories{SPORT}']");

    public WebElement getProductCategoryHeadline(){
        return driver.findElement(productCategoryHeadline);
    }

    public void selectCategory(String category){
        driver.findElement(By.cssSelector("img[test-event='select_categories{"+ category.toUpperCase() + "}']")).click();
    }
}
