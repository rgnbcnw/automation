package org.example;

import io.cucumber.java.After;
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

    @Given("^open url '(.*)'$")
    public void openUrl(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("^click the '(.*)' button$")
    public void clickButton(String button) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='"+button+"']")).click();
    }

    @Then("^assert the '(.*)' message$")
    public void message(String message) throws InterruptedException {
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath("//p[contains(text(),'"+message+"')]")).getText();
        Assert.assertEquals(message,text);
    }

    @When("^choose what color is given$")
    public void colorGiven() throws InterruptedException {
        Thread.sleep(3000);
        String colorDisplay = driver.findElement(By.className("color")).getText();
        switch (colorDisplay){
            case ("YELLOW"):
                driver.findElement(By.xpath("//button[text()='yellow']")).click();
                break;
            case ("BLUE"):
                driver.findElement(By.xpath("//button[text()='blue']")).click();
                break;
        }
    }

    @When("^choose what image is given$")
    public void imageGiven() throws InterruptedException {
        Thread.sleep(3000);
        String imageDisplay = driver.findElement(By.xpath("//div[@class='image']/img")).getAttribute("Alt");
        switch (imageDisplay){
            case ("dog"):
                driver.findElement(By.xpath("//button[text()='dog']")).click();
                break;
            case ("cat"):
                driver.findElement(By.xpath("//button[text()='cat']")).click();
                break;
        }
    }

    @After
    public void endTest() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}