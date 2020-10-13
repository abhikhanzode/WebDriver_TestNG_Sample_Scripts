package TakeSS_forFailedTest;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScreenShot_FailedTestCase {
	public static WebDriver driver;

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

	@Test
	public void SelectItem() {
		driver.findElement(By.xpath("asd")).click();
	}
	// It will execute after every test execution 
	@AfterMethod
	public void tearDown(ITestResult result)
	{

		// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{
				// Create refernce of TakesScreenshot
				TakesScreenshot ts=(TakesScreenshot)driver;

				// Call method to capture screenshot
				File source=ts.getScreenshotAs(OutputType.FILE);

				// Copy files to specific location here it will save all screenshot in our project home directory and
				// result.getName() will return name of test case so that screenshot name will be same
				FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));

				System.out.println("Screenshot taken");
			} 
			catch (Exception e)
			{

				System.out.println("Exception while taking screenshot "+e.getMessage());
			}  



		}
		// close application
		driver.quit();
	}




}
