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
import java.util.List;

public class stepDefinition {

    public WebDriver driver;

    @Given("^open url '(.*)'$")
    public void openUrl(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("^delete the '(.*)' task$")
    public void validateRowToBeDeleted(String valueToDelete) throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> links = driver.findElements(By.xpath("//li[@class=\"collection_item\"]"));
        for (WebElement link : links) {
            WebElement cell = link.findElement(By.xpath("//li[@class=\"collection_item\"]"));
            String textA = cell.getText();

            if (textA.equals(valueToDelete)) {
                WebElement deleteLink = link.findElement(By.xpath("(//li[@class=\"collection_item\"]/button)[1]"));
                deleteLink.click();
                break; // Assuming only one row should match, so we break after clicking delete link
            }
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
        driver.quit();
    }
}
