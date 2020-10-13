package MediaWare;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Login_DataDriven {
	public static WebDriver driver;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
	public static XSSFRow row;


	@BeforeMethod
	public void launch () { 
		DesiredCapabilities capabilities =DesiredCapabilities.chrome(); 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito","--disable-notifications");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe"); 
		driver = new ChromeDriver(options); driver.manage().window().maximize();
		driver.get("https://qa.m-net.in/login"); //https://qa.m-net.in/login
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS); }

	@Test(dataProvider = "GetCellData", priority = 1)
	public void PrintData(String username, String password) throws Exception {
		System.out.println("Username is: "+username);
		System.out.println("Password is: "+password);
		System.out.println("------------------------------------------------------------------------");

		WebElement usernameEntr = driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/form/div[2]/input"));
		usernameEntr.clear();
		usernameEntr.sendKeys(username);

		WebElement checkbox= driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[2]/label/span"));
		Thread.sleep(3000);
		checkbox.click();

		Thread.sleep(3000);
		WebElement proceedClk = driver.findElement(By.xpath("/html/body/m-auth/div/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[3]/m-spinner-button/button"));
		proceedClk.click();

		String ExpAlertID= "User indentified successfully!";
		String UsernameAlert = driver.findElement(By.xpath("/html[1]/body[1]/m-auth[1]/div[1]/div[1]/div[1]/div[1]/div[2]/notifier-container[1]/ul[1]/li[1]/notifier-notification[1]/p[1]")).getText();

		if (ExpAlertID.equalsIgnoreCase(UsernameAlert)) {
			Thread.sleep(3000);
			WebElement passEntr = driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/form/div[2]/div/input"));
			passEntr.clear();
			passEntr.sendKeys(password);

			WebElement Login = driver.findElement(By.xpath("//*[@id=\"m_login\"]/div/div[1]/div/div[2]/div/div/div/div/div/m-login/div[3]/m-spinner-button/button/span/span"));
			Login.click();

			String ExpLoginAlert ="Logged in successfully, but device not registered. You might not receive notifications";
			String ActLoginAlert = driver.findElement(By.xpath("/html[1]/body[1]/m-modules[1]/notifier-container[1]/ul[1]/li[1]/notifier-notification[1]/p[1]")).getText();

			if(ExpLoginAlert.equalsIgnoreCase(ActLoginAlert)) {
				System.out.println(ActLoginAlert);

			}
			else {
				String passwordErrorAlert = driver.findElement(By.xpath("/html[1]/body[1]/m-auth[1]/div[1]/div[1]/div[1]/div[1]/div[2]/notifier-container[1]/ul[1]/li[1]/notifier-notification[1]/p[1]")).getText();
				System.out.println(passwordErrorAlert);
			}
		}
		else {
			String UsernameErrorAlert = driver.findElement(By.xpath("/html[1]/body[1]/m-auth[1]/div[1]/div[1]/div[1]/div[1]/div[2]/notifier-container[1]/ul[1]/li[1]/notifier-notification[1]/p[1]")).getText();
			System.out.println(UsernameErrorAlert);

		}
	}


	@DataProvider
	public Object[][] GetCellData() throws IOException   {
		File Source = new File("D:\\Programming Workspace\\Selenium_WebDriver\\TestNG_Scripts\\mediaware_Data.xlsx");
		FileInputStream File = new FileInputStream(Source);
		workbook = new XSSFWorkbook(File);
		sheet = workbook.getSheetAt(0);

		int rowCount = sheet.getLastRowNum();
		int cellCount = sheet.getRow(1).getLastCellNum();

		Object data[][] = new String [rowCount][cellCount];
		for (int i=1; i<=rowCount-1;i++) {
			row = sheet.getRow(i);
			for (int j=0; j<cellCount; j++) {
				cell = row.getCell(j);

				cell.setCellType(cell.CELL_TYPE_STRING);
				data[i][j]=cell.getStringCellValue();
			}

		}
		return data;

	}

	@AfterMethod
	public void logout() 
	{ 
		driver.close(); 
	}





}