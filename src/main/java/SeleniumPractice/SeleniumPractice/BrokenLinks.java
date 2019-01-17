package SeleniumPractice.SeleniumPractice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpConnection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrokenLinks 
{
    WebDriver driver;
    @BeforeMethod
    public void setup()
    {
    	System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
    	driver=new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    	driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void TestBrokenLinks()
    {
    	driver.get("http://demo.guru99.com/test/newtours/");
    	List<WebElement> list=driver.findElements(By.tagName("a"));
    	
    	Iterator<WebElement> it=list.iterator();
    	while(it.hasNext())
    	{
    		String url=it.next().getAttribute("href");
    		validateLinks(url);
    	}
    }
    
    public void validateLinks(String url1)
    {
    	HttpURLConnection connection=null;
    	try {
			URL url=new URL(url1);
			 connection=(HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			if(connection.getResponseCode()==200)
			{
				System.out.println(url1+"==>"+connection.getResponseMessage());
			}
			if(connection.getResponseCode()>404)
			{
				System.out.println(url1+"==>"+connection.getResponseMessage());
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @AfterMethod
    public void teardown()
    {
    	driver.close();
    }
    
    
}
