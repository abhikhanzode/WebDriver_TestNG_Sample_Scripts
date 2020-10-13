package ApachePOI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogicInvoice_DataDriven {
	public static WebDriver driver;
	public static Actions actions;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFCell cell;



	@BeforeTest
	public void LaunchBrowser() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.logicinvoice.com/admin/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void LoginModule() throws IOException {
		// Import Excel sheet
		File src = new File("D:\\Programming Workspace\\Selenium_WebDriver\\TestNG_Scripts\\Data.xlsx");
		// Load the file.
		FileInputStream fis = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(fis);
		// Load the sheet in which data is stored.
		sheet= workbook.getSheetAt(0);

		for (int i = 1; i<=sheet.getLastRowNum();i++){
			/*I have added test data in the cell A2 as "admin" and B2 as "password"
			 Cell A2 = row 1 and column 0. It reads first row as 0, second row as 1 and so on 
			 and first column (A) as 0 and second column (B) as 1 and so on*/ 

			// Import data for Email.
			cell = sheet.getRow(i).getCell(0);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			System.out.println("Passing "+i+"st Row Data: "+ "Username= "+cell.getStringCellValue());

			WebElement user =  driver.findElement(By.xpath("//*[@id='input-username']"));
			user.clear();
			user.sendKeys(cell.getStringCellValue());

			// Import data for password.
			cell = sheet.getRow(i).getCell(1);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			System.out.println("Passing "+i+"st Row Data: "+ "Password= "+cell.getStringCellValue());
			System.out.println("-------------------------------------------------");
			WebElement pass = driver.findElement(By.xpath("//*[@id='input-password']"));
			pass.clear();
			pass.sendKeys(cell.getStringCellValue());

			WebElement login = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/form[1]/button[1]"));
			login.click();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

			if ("Dashboard".equalsIgnoreCase(driver.getTitle())) {

				actions = new Actions(driver);
				WebElement Admin1 = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/ul[1]/li[2]/a[1]"));
				actions.moveToElement(Admin1).click().perform();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

				actions = new Actions(driver);
				WebElement Logout = driver.findElement(By.xpath("/html[1]/body[1]/header[1]/ul[1]/li[2]/ul[1]/li[1]/a[1]"));
				actions.moveToElement(Logout).click().perform();
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			}
			else {
				String error = driver.findElement(By.xpath("//*[@id=\'content\']/div/div/form/div[1]")).getText();
				System.out.println("Error Message is :"+error);
				System.out.println("Num "+i+" by passing this data user unable to logged in to logic invoice ");
				System.out.println("-------------------------------------------------");
			}
		}

	}
	@Test(priority = 2)
	public void Close() {
		driver.close();
	}
}
