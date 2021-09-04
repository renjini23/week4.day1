package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following::img")).click();
		Set<String> fromWindows = driver.getWindowHandles();
		List<String> fromWindowHandlesList = new ArrayList<String>(fromWindows);
		driver.switchTo().window(fromWindowHandlesList.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		driver.switchTo().window(fromWindowHandlesList.get(0));
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following::img")).click();
		Set<String> toWindows = driver.getWindowHandles();
		List<String> toWindowHandlesList = new ArrayList<String>(toWindows);
		driver.switchTo().window(toWindowHandlesList.get(1));
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(toWindowHandlesList.get(0));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		Alert sure=driver.switchTo().alert();
		Thread.sleep(3000);
		sure.accept();
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		
		

	}

}
