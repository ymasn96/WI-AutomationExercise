import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.WithIntelligencePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

import static org.example.utils.ConfigReader.getPassword;
import static org.example.utils.ConfigReader.getUsername;


public class MyStepdefs {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    WithIntelligencePage WithIntelligencePage = new WithIntelligencePage();

    String BASE_URL = "https://platform.withintelligence.com/login";
    String EXPECTED_URL = "https://platform.withintelligence.com/all/insights";
    String DISCOVER_URL = "https://platform.withintelligence.com/all/discover";
    String PC_INSIGHTS_URL = "https://platform.withintelligence.com/pcfi/insights";

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() throws IOException {
        WithIntelligencePage.testValidLogin(driver, BASE_URL);
    }

    @When("I enter valid username and password")
    public void iEnterValidUsernameAndPassword() throws IOException {
        WebElement usernameField = driver.findElement(By.id("login-emailInput"));
        usernameField.sendKeys(getUsername());
        WebElement passwordField = driver.findElement(By.id("login-password"));
        passwordField.sendKeys(getPassword());
    }


    @When("I enter an invalid username and password")
    public void iEnterInValidUsernameAndPassword() throws IOException {
        WebElement usernameField = driver.findElement(By.id("login-emailInput"));
        usernameField.sendKeys("1234@gmail.com");
        WebElement passwordField = driver.findElement(By.id("login-password"));
        passwordField.sendKeys("abcd1234");
    }

    @And("I click the sign in button")
    public void iClickTheSignInButton() {
        WebElement cookiesField = driver.findElement(By.id("onetrust-accept-btn-handler"));
        cookiesField.click();

        WebElement loginButton = driver.findElement(By.id("login-submit"));
        loginButton.click();
    }

    @Then("I should be successfully logged in and redirected to homepage")
    public void iShouldBeSuccessfullyLoggedInAndRedirected() {

        wait.until(ExpectedConditions.urlToBe(EXPECTED_URL));
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_URL);
        driver.close();

    }

    @Then("I should see error message {string}")
    public void iShouldSeeErrorMessage(String errorMessage) {
        WithIntelligencePage.verifyLoginErrorMessageIsPresent(errorMessage, driver, wait);
    }

    @Given("I am on the platform homepage")
    public void iAmOnThePlatformHomepage() {
        driver.get(EXPECTED_URL);
    }

    @When("I click the explore link in the main menu")
    public void iClickTheLinkInTheMainMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@data-testid='nav-link-header-discover-link']"))).click();
    }

    @Then("I should be redirected to the discover page")
    public void iShouldBeRedirectedToDiscoverPage() {
        WithIntelligencePage.verifyDiscoverPage(driver, DISCOVER_URL);
    }

    @When("I focus on the search box in the top right corner")
    public void iFocusOnTheSearchBoxInTheTopRightCorner() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("searchInputValue"))).click();
    }

    @And("I enter the search query {string}")
    public void iEnterTheSearchQuery(String query) {
        WithIntelligencePage.searchQuery(driver, query);
    }

    @Then("I should see the title {string} and a list of results")
    public void iShouldSeeTheTitleAndAListOfResults(String queryResponse) {
        WithIntelligencePage.queryResults(wait, queryResponse);
    }

    @Then("I should see the message {string}")
    public void iShouldSeeTheMessage(String expectedMessage) {
        WithIntelligencePage.noQueryResults(wait, expectedMessage);

    }

    @When("I click the dropdown currently displaying Allocate With")
    public void iClickTheDropdownCurrentlyDisplaying(String arg0) {
        WebElement dropdownField = driver.findElement(By.id("downshift-0-toggle-button"));
        dropdownField.click();
    }

    @And("I select Private Credit from the dropdown")
    public void iSelectFromTheDropdown(String arg0) {
        WebElement dropdownElement = driver.findElement(By.xpath("//div[contains(text(), 'Private Credit')]"));
        dropdownElement.click();
    }

    @Then("I should be redirected to the Insights page for Private Credit")
    public void iShouldBeRedirectedToTheInsightsPageForPrivateCredit() {
        wait.until(ExpectedConditions.urlToBe(PC_INSIGHTS_URL));
        Assert.assertEquals(driver.getCurrentUrl(), PC_INSIGHTS_URL);
        driver.close();
    }
}


