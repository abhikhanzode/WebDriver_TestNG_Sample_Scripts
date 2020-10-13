package SearchDropDown;

import java.awt.RenderingHints.Key;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Brandfactory_Search {
	public static WebDriver driver;
	public static Actions action;
	

		@BeforeTest
		public void Launch() {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.get("https://www.brandfactoryonline.com/"); //https://qa.m-net.in/login
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		}
		
		@Test(priority = 1)
		public void Search() throws InterruptedException {
			WebElement search = driver.findElement(By.id("search-input-field"));
			search.sendKeys("boy");
			Thread.sleep(4000);
			search.sendKeys(Keys.ARROW_DOWN);
			search.sendKeys(Keys.ENTER);

			
		}
		
		@AfterTest
		public void Close() {
			driver.close();
		}
		
}
