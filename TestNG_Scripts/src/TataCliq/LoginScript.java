package TataCliq;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.poifs.property.Parent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginScript {
	public static WebDriver driver;
	public static Actions action;
	

		@BeforeTest
		public void Launch() {
//			ChromeOptions options = new ChromeOptions();
//			options.addArguments("--disable-notifications");
//			
			
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito","--disable-notifications");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();

			driver.get("https://qa.m-net.in/login"); //https://qa.m-net.in/login
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		}
		
		@Test(priority = 1)
		public void Login() {
			action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			WebElement SignIn_SignUpClk = driver.findElement(By.xpath("//div[contains(text(),'Sign in')]"));
			action.moveToElement(SignIn_SignUpClk).build().perform();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			WebElement Login_RegisterClk = driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
			action.moveToElement(Login_RegisterClk).click().build().perform();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			WebElement username = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/div[1]/div/div/input"));
			username.sendKeys("khanzodeabhijeet@gmail.com");
			
			WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/div[2]/div/div[1]/div[2]/div/input"));
			password.sendKeys("@2hijeet");
			
			WebElement LoginClk = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/div[2]/div/div[2]/div/div/div"));
			LoginClk.click();
			
			System.out.println("Title of Page is: "+driver.getTitle());
			
			
		}
		@Test(priority = 2)
		public void Category() {
			action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			WebElement categoryOvr = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]"));
			action.moveToElement(categoryOvr).build().perform();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			
			WebElement electronicsOvr= driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[4]"));
			action.moveToElement(electronicsOvr).build().perform();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			WebElement smartphonesClk= driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[4]"));
			action.moveToElement(smartphonesClk).click().build().perform();
			System.out.println("Title of Page is: "+driver.getTitle());

		}
		
		@Test(priority = 3)
		public void OpenProduct() throws InterruptedException {
			action = new Actions(driver);
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String parent = driver.getWindowHandle();

			WebElement selectPhone = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[3]/div[2]/div[1]/div/div/div/div/div[1]/div[1]/div/div/a/div"));
			action.moveToElement(selectPhone).click().build().perform();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	
			
			Set<String> allWindows = driver.getWindowHandles();
			int count = allWindows.size();
			
			for (String child:allWindows) {
				if(!parent.equalsIgnoreCase(child)) {
					driver.switchTo().window(child);
					Thread.sleep(3000);
					driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);	

					WebElement buynow = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div/div[1]/div[2]/div[3]/div[1]/div"));
					action.moveToElement(buynow).click().build().perform();	
				}
			}		
		}
		
		@AfterTest
		public void Close() {
			//driver.close();
		}
}
