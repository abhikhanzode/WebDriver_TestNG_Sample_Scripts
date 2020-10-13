package BrandFactory;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Script_1 {

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
		public void Login() {
			action = new Actions(driver);
			WebElement ProfClk = driver.findElement(By.xpath("//*[@id=\"goto_my_account\"]/img"));
			action.moveToElement(ProfClk).build().perform();
			
			WebElement Login = driver.findElement(By.linkText("LOG IN"));
			action.moveToElement(Login).click().perform();
			
			WebElement username = driver.findElement(By.xpath("//*[@id=\"login-email\"]"));
			username.sendKeys("8237536973");
			
			WebElement password = driver.findElement(By.xpath("//*[@id=\"login-pswd\"]"));
			password.sendKeys("@2hijeet");
			
			WebElement Loginbtn = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/div[2]/div/div[2]/form/div/div[4]/button"));
			Loginbtn.click();
		}
		
		@Test(priority = 2)
		public void AddToBag() {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			action = new Actions(driver);
			WebElement MEN = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/a[1]/picture[1]/img[1]"));
			action.moveToElement(MEN).click().build().perform();
			
			
			WebElement CardOver = driver.findElement(By.xpath("//*[@id=\"prodBox\"]/li[1]/div[3]/a/div[1]/picture/img"));
			action.moveToElement(CardOver).build().perform();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			WebElement AddtoBag = driver.findElement(By.xpath("//*[@id=\"prodBox\"]/li[1]/div[3]/a/div[2]/div[1]/button[1]"));
			action.moveToElement(CardOver).click().perform();
				
		}
		
		@AfterTest
		public void Close() {
			driver.close();
		}
	}
