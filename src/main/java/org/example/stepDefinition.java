package org.example;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class stepDefinition {

    public WebDriver driver;

    @Given("^open url '(.*)'$")
    public void openWebPage(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
    }

    @When("^click '(.*)' '(.*)'$")
    public void click(String tag, String locator){
        switch(tag){
            case("link"):
                driver.findElement(By.linkText(locator)).click();
                break;
            case("button"):
                driver.findElement(By.xpath("//button[text()='"+locator+"']")).click();
                break;
            case("checkbox"):
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Hand Saw')]")));
                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
                driver.findElement(By.xpath("//label[contains(text(),'"+locator+"')]")).click();
                break;
        }
    }

    @When("^type '(.*)' to '(.*)' by '(.*)'$")
    public void typeTo (String valueToType, String field, String elementLocator){
        switch (elementLocator){
            case ("id"):
                driver.findElement(By.id(field)).sendKeys(valueToType);
                break;
            case ("name"):
                driver.findElement(By.name(field)).sendKeys(valueToType);
                break;
            case ("className"):
                driver.findElement(By.className(field)).sendKeys(valueToType);
                break;
            case ("cssSelector"):
                driver.findElement(By.cssSelector(field)).sendKeys(valueToType);
                break;
        }
    }

    @When("^select to '(.*)' by value '(.*)'$")
    public void clickDropdown(String dropDown, String value){
        WebElement selectElement = driver.findElement(By.className(dropDown));
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    @When("^click card-body '(.*)'$")
    public void cardBody(String picture){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a//h5[contains(text(),'Wood Saw')]")));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
        driver.findElement(By.xpath("//a//h5[contains(text(),'"+picture+"')]")).click();
    }

    @When("^click Add to Cart button$")
    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='btn-add-to-cart']")));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,200)");
        element.click();
    }

    @Then("^assert cart count$")
    public void cartCount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='lblCartCount']")));
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
        String count = driver.findElement(By.xpath("//span[@id='lblCartCount']")).getText();
        Assert.assertEquals("1",count);
    }

    @After
    public void afterTest() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }




}