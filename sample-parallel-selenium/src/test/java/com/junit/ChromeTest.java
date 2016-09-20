package com.junit;

import com.labs.common.Constants;
import com.labs.utils.BaseCoppertoneTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class ChromeTest extends BaseCoppertoneTest{

    @Test
    public void runSampleTest() throws Exception {
        driver.get(Constants.URL);
        isFeedbackPopUpDisplayed();
        homePage.getProductsLink().click();
        waitForElement(productCategoryPage.productCategoryHeadline);
        Assert.assertEquals(
                "FIND YOUR PRODUCT",
                productCategoryPage.getProductCategoryHeadline().getText());
        productCategoryPage.selectCategory("sport");

        String category = "sport";
        String firstProduct = "Coppertone SPORT® Continuous Sunscreen Spray";
        String secondProduct = "Coppertone SPORT® Sunscreen Continuous Spray PRO SERIES with DURAFLEX";

        productDetailsPage.setProductTitle(firstProduct,category);
        waitForElement(productDetailsPage.getProductTitleSelector());
        Assert.assertEquals(
                firstProduct,
                productDetailsPage.getProductTitle().getText());
        waitForLoadingBarDismissed();
        productDetailsPage.clickAddToCartBtn();
        waitForLoadingBarDismissed();
        waitForElement(productDetailsPage.addToCartPopUp.getAddToCartSuccessSelector());

        productDetailsPage.addToCartPopUp.getContinueShoppingBtn().click();

        waitForElement(productCategoryPage.productCategoryHeadline);

        productCategoryPage.selectCategory(category);

        waitForElement(productDetailsPage.getProductTitleSelector());
        waitForLoadingBarDismissed();

        productDetailsPage.setProductTitle(secondProduct,category);
        productDetailsPage.clickSideProduct(secondProduct);
        waitForElement(productDetailsPage.getProductTitleSelector());
        Assert.assertEquals(secondProduct,productDetailsPage.getProductTitle().getText());
        productDetailsPage.clickAddToCartBtn();

        Assert.assertTrue(productDetailsPage.addToCartPopUp.getAddToCartSuccess().isDisplayed());

        productDetailsPage.addToCartPopUp.getGoToCartBtn().click();
        waitForLoadingBarDismissed();
        waitForElement(productDetailsPage.getShoppingCart().getShoppingCartTitleSelector());
        Assert.assertEquals("SHOPPING CART",productDetailsPage.getShoppingCart().getShoppingCartTitle().getText());
        delay(5000);
    }
}
