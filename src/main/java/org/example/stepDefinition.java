package org.example;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class stepDefinition {

    public WebDriver driver;

    @Given("^open url https://www.saucedemo.com/$")
    public void sauceDemo(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("^login using '(.*)' and '(.*)'$")
    public void logIn(String userName, String password){
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
    }

    @Then("^user is logged in$")
    public void button(){
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
    }

    @And("^sort the product by name z to a$")
    public void filterProduct(){
        driver.findElement(By.xpath("//option[contains(text(),'Name (Z to A)')]")).click();
    }

    @And("^add to cart the Sauce Labs Onesie$")
    public void addToCart(){
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @And("^checkout$")
    public void checkout(){
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }

    @And("^provide the FirstName and LastName$")
    public void userInfo(){
        driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Test");
    }

    @And("^provide the ZIP$")
    public void zip(){
        driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("123456");
    }

    @And("^click continue$")
    public void continueButton(){
        driver.findElement(By.xpath("//input[@id='continue']")).click();
    }

    @And("^assert the Checkout:Overview$")
    public void assertPage(){
        String name = driver.findElement(By.xpath("//span[contains(text(),'Checkout: Overview')]")).getText();
        Assert.assertEquals("Checkout: Overview",name);
    }

    @And("^click finish$")
    public void finishButton(){
        driver.findElement(By.xpath("//button[@id='finish']")).click();
    }

    @Then("^assert the Thank you for your order$")
    public void assertPage1(){
        String name = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your order!')]")).getText();
        Assert.assertEquals("Thank you for your order!",name);
    }





    @After
    public void afteDemo() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}
