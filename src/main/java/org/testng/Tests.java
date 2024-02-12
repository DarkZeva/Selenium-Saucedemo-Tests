package org.testng;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class Tests {

    @Test
    public void validate_login(){
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

        Utility.Login("standard_user", "secret_sauce", driver);

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        driver.close();
    }

    @Test
    public void validate_login_excel(){
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

        int i = 0;
        while("" != Utility.ReadExcel("UserData", i, 0)){
            String userLogin = Utility.ReadExcel("UserData", i, 0);
            String userPassword = Utility.ReadExcel("UserData", i, 1);
            Utility.Login(userLogin, userPassword, driver);
            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertEquals(expectedUrl, actualUrl);
            driver.navigate().back();
            i++;
        }
    }

    @Test
    public void test_navigation_buttons(){
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://saucelabs.com/");
        driver.navigate().to("https://saucelabs.com/pricing");

        driver.navigate().back();
        String expectedUrl = "https://saucelabs.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);

        driver.navigate().forward();
        driver.navigate().refresh();
        expectedUrl = "https://saucelabs.com/pricing";
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        driver.close();
    }

    @Test
    public void test_side_menu(){
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "E:\\Chrome\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
        Utility.Login("standard_user", "secret_sauce", driver);

        WebElement button = driver.findElement(By.id("react-burger-menu-btn"));
        button.isDisplayed();
        button.isEnabled();
        button.click();

        WebElement button2 = driver.findElement(By.id("about_sidebar_link"));
        button2.isDisplayed();
        button2.isEnabled();
        button2.click();
        driver.close();
    }

}
