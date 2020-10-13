package DevLink;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Script_1 {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://qa.m-net.in/login"); //https://qa.m-net.in/login
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		WebElement username= driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/form/div[2]/input"));
		username.sendKeys("Sanyuktaa");
		
		WebElement agree = driver.findElement(By.xpath("/html[1]/body[1]/m-auth[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/m-login[1]/div[2]/label[1]/span[1]"));
		agree.click();
		
		WebElement proceed= driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[3]/m-spinner-button/button"));
		proceed.click();
		
		WebElement password = driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/form/div[2]/div/input"));
		password.sendKeys("Tt@123456");
		
		WebElement login =driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[3]/m-spinner-button/button/span/span"));
		login.click();
		Thread.sleep(4000);
		
		WebElement jobBtl=driver.findElement(By.xpath("//*[@id=\"m_quicksearch\"]/m-portlet/div/div[3]/perfect-scrollbar/div/div[1]/div/div/div[1]"));
		jobBtl.click();
		
		Thread.sleep(3000);
		
		driver.switchTo().frame(0);

		Actions action = new Actions(driver);
//		
		WebElement sidebar = driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]"));
		action.moveToElement(sidebar).build().perform();
//		
		/*
		 * WebElement menu =
		 * driver.findElement(By.xpath("//*[@id=\"minimizeSidebar\"]"));
		 * action.moveToElement(menu).click().perform();
		 */
		
		Thread.sleep(4000);
		
		WebElement myjob = driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]/app-sidebar/div[2]/ul/li[2]/a"));
		action.moveToElement(myjob).click().perform();
		
		WebElement createJob = driver.findElement(By.partialLinkText("Create Job"));
		action.moveToElement(createJob).click().perform();
		
		
		
		

	}

}
