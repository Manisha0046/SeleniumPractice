package SeleniumPractice.SeleniumPractice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class UploadUsingRobot {
	public static WebDriver driver;
	@BeforeMethod
	 public void BeforeMethod1() 
	 {
		System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");

		driver=new ChromeDriver();
		 driver.get("http://demo.guru99.com/test/upload/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	 }
	@Test
	public void uploadFile() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		StringSelection str=new StringSelection("I:\\OrangeHRM\\SeleniumPractice\\pom.xml");
		Clipboard clipboard=kit.getSystemClipboard();
		clipboard.setContents(str, null);
		
		driver.findElement(By.name("uploadfile_0")).click();
		try {
			Robot robot=new Robot();
			robot.delay(300);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(300);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		//<button type="button" id="submitbutton" name="send" class="btn buttoncolor has-spinner" style="color:#000;"> <span class="spinner"><img src="images/spinner.gif" width="15" height="15"></span>Submit File</button>
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("document.getElementById('submitbutton').click();");
	
	}
	@AfterMethod
    public void teardown()
    {
    	driver.close();
    }
}
