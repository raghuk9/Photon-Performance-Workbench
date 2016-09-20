package com.labs.utils;

import com.labs.common.BaseTest;
import com.labs.pages.AbstractPage;
import com.labs.pages.HomePage;
import com.labs.pages.ProductCategoryPage;
import com.labs.pages.ProductDetailsPage;

/**
 * Created by Irfan Mauludin on 5/16/16.
 */
public class BaseCoppertoneTest extends BaseTest{

    protected HomePage homePage;
    protected ProductCategoryPage productCategoryPage;
    protected ProductDetailsPage productDetailsPage;

    @Override
    public void setPages(AbstractPage abstractPage) {
        super.setPages(abstractPage);
    }

    @Override
    public void setUp() {

        super.setUp();
        homePage = new HomePage(driver);
        productCategoryPage = new ProductCategoryPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
    }
}
