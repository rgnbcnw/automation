package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class stepDef {

    public WebDriver driver;
    public WebDriverWait wait;

    @Given("^open url https://thelab.boozang.com/visualBugs$")
    public void openUrl(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://thelab.boozang.com/visualBugs");
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    @When("^assert the name and image is the same$")
    public void figureShown(){
        String imageShown = driver.findElement(By.xpath("//div[@class='apect_ratio_inside']/img")).getAttribute("alt");
        String nameShown = driver.findElement(By.xpath("//div[@class='label_wrapper']/p")).getText();
        if(nameShown.equalsIgnoreCase(imageShown)){
            System.out.println(imageShown + " and " + nameShown + " are matched");
        }else {
            System.out.println(imageShown + " and " + nameShown + " are not matched");
        }
    }

    @Then("^click the NEXT IMAGE button$")
    public void button(){
        driver.findElement(By.xpath("//button[@class='form_btn add']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='apect_ratio_inside']/img")));
    }
}
