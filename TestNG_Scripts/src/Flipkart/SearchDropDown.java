package Flipkart;

import java.util.Iterator;
import java.util.Set;
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

public class SearchDropDown {
	public static WebDriver driver;
	public static Actions action;


	@BeforeTest
	public void Launch() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://www.flipkart.com/"); //https://qa.m-net.in/login
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void Search() throws InterruptedException {

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iterator = windows.iterator();
		String mainWindow = iterator.next();		
		driver.switchTo().window(mainWindow);
		Thread.sleep(3000);

		WebElement cancel = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/button[1]"));
		cancel.click();
		Thread.sleep(3000);

		WebElement search = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input"));
		search.sendKeys("redmi");
		Thread.sleep(4000);
		for(int i=1; i<=2; i++) {
			search.sendKeys(Keys.ARROW_DOWN);
		}
		search.sendKeys(Keys.ENTER);

	}

	@AfterTest
	public void Close() {
		driver.close();
	}
}
