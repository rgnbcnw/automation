package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class stepDefinition {

    public WebDriver driver;

    @Given("^open url '(.*)'$")
    public void xyzBank(String url){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("^click on main menu '(.*)'$")
    public void clickMainMenu(String mainButton) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'"+mainButton+"')]")).click();
    }

    @When("^click on '(.*)' in Menu tab$")
    public void clickManagerMenu(String buttonManager) throws InterruptedException {
        Thread.sleep(2000);
        switch (buttonManager){
            case("Add Customer"):
            case("Transactions"):
                driver.findElement(By.xpath("//button[@ng-class='btnClass1']")).click();
                break;
            case("Open Account"):
            case("Deposit"):
                driver.findElement(By.xpath("//button[@ng-class='btnClass2']")).click();
                break;
            case("Customers"):
            case("Withdrawal"):
                driver.findElement(By.xpath("//button[@ng-class='btnClass3']")).click();
                break;
        }
    }

    @And("^enter '(.*)', '(.*)' and '(.*)'$")
    public void customerInfo(String firstName, String lastName, String postCode) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@ng-model='fName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@ng-model='lName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@ng-model='postCd']")).sendKeys(postCode);
    }

    @Then("^click on Add Customer Button$")
    public void buttonAddCustomer() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Add Customer']")).click();
        driver.switchTo().alert().accept();
    }

    @When("^selects '(.*)' in '(.*)' and '(.*)' in '(.*)'$")
    public void selectCustomer(String customerName, String userSelect, String currency, String currencySelect) throws InterruptedException {
        Thread.sleep(2000);
        selectDropdown(customerName, userSelect);
        selectDropdown(currency, currencySelect);
        driver.findElement(By.xpath("//button[text()='Process']")).click();
        driver.switchTo().alert().accept();
    }

    @When("^select '(.*)' in '(.*)'$")
    public void selectDropdown(String valueToSelect, String select) throws InterruptedException {
        Thread.sleep(2000);
        Select dropdown = new Select(driver.findElement(By.id(select)));
        dropdown.selectByVisibleText(valueToSelect);
    }

    @Then("^click on '(.*)' button$")
    public void clickButton(String clickButton) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='"+clickButton+"']")).click();
    }

    @And("^enter '(.*)' amount in '(.*)'$")
    public void enterAmount(String amount, String transaction) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@ng-model='amount']")).sendKeys(amount);
        clickButton(transaction);
    }

    @And("^assert '(.*)' Transaction$")
    public void transaction(String transactionName) throws InterruptedException {
        Thread.sleep(2000);
        String name = driver.findElement(By.xpath("//td[contains(text(),'"+transactionName+"')]")).getText();
        Assert.assertEquals(transactionName,name);
    }

    @And("^assert '(.*)'$")
    public void successfulTransaction(String confirmation) throws InterruptedException {
        Thread.sleep(2000);
        String successTransact = driver.findElement(By.xpath("//span[contains(text(),'"+confirmation+"')]")).getText();
        Assert.assertEquals(confirmation, successTransact);
    }

    @And("^delete the customer '(.*)' information$")
    public void validateRowToBeDeleted(String valueToDelete) {
        List<WebElement> links = driver.findElements(By.cssSelector("tbody td:nth-child(1)"));
        for (WebElement link : links) {
            WebElement cell = link.findElement(By.cssSelector("tbody td:nth-child(1)"));
            String textA = cell.getText();

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", link);

            if (textA.equals(valueToDelete)) {
                WebElement deleteLink = link.findElement(By.cssSelector("tbody td:nth-child(5) button"));
                deleteLink.click();
                break; // Assuming only one row should match, so we break after clicking delete link
            }
        }
    }


}
