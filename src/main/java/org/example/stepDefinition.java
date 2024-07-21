package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class stepDefinition {

    public WebDriver driver;
    public WebDriverWait wait;

    @Given("^open url '(.*)'$")
    public void openUrl(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    @When("^delete the '(.*)' task$")
    public void validateRowToBeDeleted(String valueToDelete) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> deleteButton = driver.findElements(By.xpath("//li[@class=\"collection_item\"]"));
        String elementText;
        for(WebElement button : deleteButton){
            WebElement deleteLink = driver.findElement(By.xpath("//li[@class=\"collection_item\"]/button"));
            deleteLink.click();
            Thread.sleep(2000);
        }
    }

    @When("^add the '(.*)' task$")
    public void addTask(String toDo) throws InterruptedException {
        driver.findElement(By.cssSelector("label input")).sendKeys(toDo);
        driver.findElement(By.cssSelector("section div button")).click();
        Thread.sleep(2000);
    }

    @When("^assert Your Schedule is full$")
    public void schedule() throws InterruptedException {
        String error = driver.findElement(By.xpath("//h5[text()='Your schedule is full!']")).getText();
        Assert.assertEquals("Your schedule is full!", error);
        System.out.println("ur schedule is full");
        Thread.sleep(2000);
//        driver.quit();
    }
}
