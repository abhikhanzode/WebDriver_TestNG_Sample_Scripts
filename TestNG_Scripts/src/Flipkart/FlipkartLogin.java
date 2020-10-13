package Flipkart;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlipkartLogin {
	public static WebDriver driver;

		@BeforeTest
		public void Launch() {
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}

		@Test(priority = 1)
		public void Login() {
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> iterator = windows.iterator();

			String mainWindow = iterator.next();

			driver.switchTo().window(mainWindow);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			WebElement username = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input"));
			username.clear();
			username.sendKeys("9075758469");

			WebElement password = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input"));
			password.clear();
			password.sendKeys("@2hijeet");

			WebElement submit = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button"));
			submit.click();
		}

		@Test(priority = 2)
		public void SelectItem() throws Exception {
	     

			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			Thread.sleep(4000);

			Actions action = new Actions(driver);
			WebElement electronic = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[2]/div/ul/li[1]/span"));
			action.moveToElement(electronic).build().perform();
			
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			WebElement mi = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/ul/li[1]/ul/li/ul/li[2]/ul/li[3]/a"));
			action.moveToElement(mi).click().perform();

		}
		
	}
