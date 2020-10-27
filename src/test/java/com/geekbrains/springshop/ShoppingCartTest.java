package com.geekbrains.springshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class ShoppingCartTest {

    private WebDriver driver;

    @BeforeSuite
    public void init() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void putProductToSoppingCart(){
        driver.get("http://localhost:8189/springshop/shop");
        List <WebElement> webElements = driver.findElements(By.cssSelector(".responsive-row"));
        WebElement webElement = webElements.get(0);
        String productTitle = webElement.findElement(By.cssSelector(".font-weight-bold")).getText();
        webElement.findElement(By.cssSelector(".btn-light")).click();
        driver.get("http://localhost:8189/springshop/cart");
        List<WebElement> products = driver.findElements(By.cssSelector(".text-light"));
        Assert.assertEquals(products.get(1).getText(), productTitle);
    }

    @AfterSuite
    public void shutdown() {
        this.driver.quit();
    }

}
