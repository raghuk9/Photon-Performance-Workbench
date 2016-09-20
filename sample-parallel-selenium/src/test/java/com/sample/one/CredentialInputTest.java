package com.sample.one;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by Irfan Mauludin on 8/3/16.
 */
public class CredentialInputTest extends ParallelTest {

    @Test
    public void inputCredentialTest() throws InterruptedException {
        // Pre-requisite:
        // - set the random user index
        int randomNum = getRandomIndex();

        System.out.println("Steps:");
        System.out.println("1. Wait until home page displayed");
        wait("img[test-event='open_home']");
        System.out.println("2. Tap 'Hamburger' Menu");
        waitAndClick(By.cssSelector("label[test-event='open_hamburger_menu']"));
        System.out.println("3. Tap 'Account' tab from 'Hamburger' menu");
        waitAndClick(By.cssSelector("span#account[test-event='open_tab_selection']"));
        System.out.println("4. Tap 'Sign In' option");
        waitAndClick(By.cssSelector("#ui-id-3 > div > div > ul > li.authorization-link > a"));
        Thread.sleep(5000);
        System.out.println("5. Verify whether the sign in page displayed");
        wait("span.base");
        System.out.println("credential : ");
        System.out.println("- user random index : " + randomNum);
        System.out.println("- username : " + getUsers().get(randomNum).getUsername());
        System.out.println("- password : " + getUsers().get(randomNum).getPassword());
        System.out.println("------------------------------------");
        System.out.println("6. Enter username");
        inputValue(By.cssSelector("input[test-input-field='email']"),getUsers().get(randomNum).getUsername());
        System.out.println("7. Enter password");
        inputValue(By.cssSelector("input[test-input-field='password']"),getUsers().get(randomNum).getPassword());
        System.out.println("8. Tap 'Login' button");
        waitAndClick(By.cssSelector("button[test-event='login_success,login_error']"));
        Thread.sleep(3000);
    }
}
