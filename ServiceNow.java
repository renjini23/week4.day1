package week4.day1;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://dev113545.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");
		driver.findElement(By.id("sysverb_login")).click();
		driver.switchTo().defaultContent();
		WebElement search = driver.findElement(By.id("filter"));
		search.click();
		search.sendKeys("incident");
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList=new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		driver.findElement(By.linkText("Alissa Mountjoy")).click();
		driver.switchTo().window(windowHandlesList.get(0));
		driver.switchTo().frame("gsft_main");
		//driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).click();
		//Thread.sleep(3000);
		//driver.findElement(By.xpath("//span[@class='icon icon-lightbulb']")).click();
		driver.findElement(By.id("lookup.incident.short_description")).click();
		//driver.switchTo().window(windowHandlesList.get(1));
		Set<String> windowHandlesShortDesc = driver.getWindowHandles();
		List<String> ShortDesc=new ArrayList<String>(windowHandlesShortDesc);
		driver.switchTo().window(ShortDesc.get(1));
		driver.findElement(By.linkText("Request for help")).click();
		driver.switchTo().window(ShortDesc.get(0));
		driver.switchTo().frame("gsft_main");
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		driver.findElement(By.xpath("//span[text()='Press Enter from within the input to submit the search.']/following-sibling::input")).sendKeys(incidentNumber,Keys.ENTER);
		driver.findElement(By.xpath("//td[@class='vt']/a")).click();
		String checkNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		if(checkNumber.equals(incidentNumber)) {
			System.out.println("Incident " + incidentNumber +" is created successfully");
		}
		else
			System.out.println("Incident is not successfull");
		File src = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/NewIncident.png");
		FileUtils.copyFile(src, dest);
        driver.close();
	}

}
