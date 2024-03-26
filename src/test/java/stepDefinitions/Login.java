package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

	private static WebDriver driver = null;
	static WebDriverWait wait;

	@Before
	public static void before() {
		ChromeOptions options = new ChromeOptions().addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}

	@Given("I am in the facebook main page")
	public void i_am_in_the_facebook_main_page() {
		driver.get("https://www.facebook.com/login");
	}

	@When("I login using {string} and {string}")
	public void i_login_using_and(String username, String password) {
		// Credentials can be changed in the login.feature file
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("loginbutton")).click();
	}

	@Then("I should be able to post a status message")
	public void i_should_be_able_to_post_a_status_message() {
		// Check for welcome page if the account has not finished initial setup
		// If found go to main page
		if (driver.getCurrentUrl().contains("welcome")) {
			driver.get("https://www.facebook.com/");
		}
		// Wait for the input to be clickable then click each one
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[@class='xi81zsa x1lkfr7t xkjl1po x1mzt3pk xh8yej3 x13faqbe']//span[@class='x1lliihq x6ikm8r x10wlt62 x1n2onr6']")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//p[@class='xdj266r x11i5rnm xat24cr x1mh8g0r x16tdsg8']"))).click();

		// Create post
		driver.findElement(By.xpath("//p[@class='xdj266r x11i5rnm xat24cr x1mh8g0r x16tdsg8']"))
				.sendKeys("Hello World");
		driver.findElement(By.xpath("//span[text()='Post']")).click();

		// Confirm that the post was created
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Hello World']")));

		// TO DO: delete the post so repeated test using the same user does not trigger
		// facebook's repeat post policy. Currently it's out of the test scope
	}

	@After
	public static void after() {
		driver.close();
	}

}
