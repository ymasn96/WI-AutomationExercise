package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class WithIntelligencePage {

    public WithIntelligencePage() {
    }


    public void testValidLogin(WebDriver driver, String BASE_URL) throws IOException {
        driver.get(BASE_URL);
    }

    public void verifyLoginErrorMessageIsPresent(String errorMessage, WebDriver driver, WebDriverWait wait) {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@data-testid='login-errorMessage']")));
        WebElement loginError = driver.findElement(By.xpath("//p[@data-testid='login-errorMessage']"));
        Assert.assertEquals(loginError.getText(), errorMessage);
        driver.close();
    }


    public void verifyDiscoverPage(WebDriver driver, String DISCOVER_URL) {
        Assert.assertEquals(driver.getCurrentUrl(), DISCOVER_URL);
        driver.close();
    }

    public void searchQuery(WebDriver driver, String query) {
        WebElement searchBox = driver.findElement(By.id("searchInputValue"));
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public void queryResults(WebDriverWait wait, String queryResponse) {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), 'Displaying results for " + queryResponse + "')]")));
        Assert.assertTrue(message.isDisplayed());
        WebElement resultsList = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'infinite-scroll-component')]")));
        Assert.assertTrue(resultsList.findElements(By.tagName("div")).size() > 0);
    }

    public void noQueryResults(WebDriverWait wait, String expectedMessage) {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(), 'Your search for " + expectedMessage + "')]")));
        Assert.assertTrue(message.isDisplayed());
    }
}
