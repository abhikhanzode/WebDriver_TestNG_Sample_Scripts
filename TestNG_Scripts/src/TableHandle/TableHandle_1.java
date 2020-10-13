package TableHandle;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;

public class TableHandle_1 {
	public static WebDriver driver;
	public static Actions action;
	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("https://money.rediff.com/gainers/bse/daily/groupa"); //https://qa.m-net.in/login
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);


		// 1. Print all headers of web table:
		System.out.println("1. Print all headers of web table:------------------");
		// Printing table header of a web table assuming first row as header
		List<WebElement> allHeadersOfTable= driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/thead/tr/th"));
		System.out.println("Headers in table are below:");
		System.out.println("Total headers found: "+allHeadersOfTable.size());
		for(WebElement header:allHeadersOfTable)
		{
			System.out.print("| "+header.getText());
		}
		System.out.println("");
		System.out.println("-----------------------------------------------------");

		System.out.println("2.Number of table rows-------------------------------");
		// Finding number of rows in a web table. We need to exclude header to get actual number of data rows
		List allRows= driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr"));
		System.out.println("Total data rows found in table:"+ (allRows.size()));
		System.out.println("-----------------------------------------------------");


		List allRows_2= driver.findElements(By.xpath("//*[@id=\"leftcontainer\"]/table/tbody/tr"));
		// Print book name whose price is greater than and equal to 1000
		System.out.println("========================================================================");
		System.out.println("Books with price greater than and equal to 1000 are below:");

		for(int j=1;j<=allRows_2.size();j++) 
		{ 
			String priceColumn=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[5]/table[1]/tbody[1]/tr["+j+"]/td[3]")).getText(); 
			String s = (priceColumn).replaceAll(",", ""); 
			double price =Double.parseDouble(s);
			if(price>=500.00 && price<=1000.00)
			{
				WebElement bookName=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[5]/table[1]/tbody[1]/tr["+j+"]/td[1]"));
				WebElement priceColumn_1=driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[5]/table[1]/tbody[1]/tr["+j+"]/td[3]"));
				System.out.println(j+") "+bookName.getText()+" => "+priceColumn_1.getText());
			}
		}
		System.out.println("========================================================================");


		driver.close();
	}

}
