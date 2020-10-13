package DevLink;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateJOB_Script_2 {
	public static WebDriver driver;
	public static Actions action;

	@BeforeTest
	public void Launch() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito", "--disable-notifications");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		driver.get("https://dev.m-net.in/login"); // https://qa.m-net.in/login
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void LoginModule() throws InterruptedException {
		WebElement username = driver.findElement(
				By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/form/div[2]/input"));
		username.sendKeys("Sanyuktaa");

		WebElement agree = driver.findElement(By.xpath(
				"/html[1]/body[1]/m-auth[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/m-login[1]/div[2]/label[1]/span[1]"));
		agree.click();

		WebElement proceed = driver.findElement(By.xpath(
				"//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[3]/m-spinner-button/button"));
		proceed.click();

		WebElement password = driver.findElement(By
				.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/form/div[2]/div/input"));
		password.sendKeys("Tt@123456");

		WebElement login = driver.findElement(By.xpath(
				"//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[3]/m-spinner-button/button/span/span"));
		login.click();

		// Handle Security PopUp
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Set<String> win = driver.getWindowHandles();
		Iterator<String> iterator = win.iterator();

		String Alertpop = iterator.next();

		driver.switchTo().window(Alertpop);

		WebElement confirm = driver.findElement(By.xpath(
				"/html/body/div[3]/div[2]/div/mat-dialog-container/m-login-warning-dialog/div[2]/div/m-spinner-button/button"));
		confirm.click();

	}

	@Test(priority = 2)
	public void BTL_Job() throws Exception {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(4000);

		WebElement btlJobClk = driver.findElement(By
				.xpath("//*[@id=\"m_quicksearch\"]/m-portlet/div/div[3]/perfect-scrollbar/div/div[1]/div/div/div[1]"));
		btlJobClk.click();

	}

	@Test(priority = 3)
	public void Open_CreateJOB() throws Exception {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		Thread.sleep(3000);

		driver.switchTo().frame(0);

		Actions action = new Actions(driver);
		//		
		WebElement sidebar = driver.findElement(By.xpath("/html/body/app-root/app-admin/div/div[2]/app-sidebar/div[2]/ul/li[2]/a"));
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
