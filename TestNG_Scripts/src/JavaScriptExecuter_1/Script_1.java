package JavaScriptExecuter_1;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Script_1 {

	public static WebDriver driver;

	@BeforeTest
	public void Launch() {
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
	}
	
	@Test(priority = 1)
	public void Login() throws InterruptedException {
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
		Thread.sleep(4000);
		
		WebElement element = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/ul/li[1]/span"));
		mouseHoverJScript(element);
	}


	public void mouseHoverJScript(WebElement HoverElement) {
		try {
			if (isElementPresent(HoverElement)) {

				String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');"
						+ "evObj.initEvent('mouseover', true, false); "
						+ "arguments[0].dispatchEvent(evObj);} "
						+ "else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
				((JavascriptExecutor) driver).executeScript(mouseOverScript,
						HoverElement);

			} else {
				System.out.println("Element was not visible to hover " + "\n");

			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + HoverElement
					+ "is not attached to the page document"+ e.getStackTrace());

		}

	}
		public static boolean isElementPresent(WebElement element) {
			boolean flag = false;
			try {
				if (element.isDisplayed()
						|| element.isEnabled())
					flag = true;
			} catch (NoSuchElementException e) {
				flag = false;
			} catch (StaleElementReferenceException e) {
				flag = false;
			}
			return flag;
		}


	}
