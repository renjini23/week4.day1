package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FunWithFrames {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver=new ChromeDriver();
	driver.get("http://leafground.com/pages/frame.html");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//To take the screenshot of the click button
	driver.switchTo().frame(driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[1]")));
	WebElement clickimage = driver.findElement(By.id("Click"));
	clickimage.click();
	File src=clickimage.getScreenshotAs(OutputType.FILE);
	File dest=new File("./snaps/FrameClick2.png");
	FileUtils.copyFile(src, dest);
	Thread.sleep(2000);
	driver.switchTo().parentFrame();
	
	//To find the number of Frames
	List<WebElement> frames = driver.findElements(By.tagName("iframe"));
	System.out.println("Number of Frames on the main page is " + frames.size());
    driver.close();
	}

}
