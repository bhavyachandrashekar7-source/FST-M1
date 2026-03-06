package Activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity6 {
	
	    WebDriverWait wait;
	    AndroidDriver driver;

	    // Set up method
	    @BeforeClass
	    public void setUp() throws MalformedURLException, URISyntaxException {
	        // Desired Capabilities
	        UiAutomator2Options options = new UiAutomator2Options();
	        options.setPlatformName("android");
	        options.setAutomationName("UiAutomator2");
	        options.setAppPackage("com.android.chrome");
	        options.setAppActivity("com.google.android.apps.chrome.Main");
	        options.noReset();

	        // Server URL
	        URL serverURL = new URI("http://127.0.0.1:4723").toURL();

	        // Driver initialization
	        driver = new AndroidDriver(serverURL, options);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Open the page in browser
	        driver.get("https://training-support.net/webelements/sliders");
	    }

	  
	    
	    @Test
	    public void slide25() throws InterruptedException {
	    	Thread.sleep(6000);
	    	Dimension dim = driver.manage().window().getSize();
	    	
	    	int x= (int) (0.5 * dim.getWidth());
	    	int y= (int) (0.72 * dim.getHeight());
	    	
	    	
	    	Point start= new Point(x,y);
	    	int p= (int) (0.331 * dim.getWidth());
	    	int q= (int) (0.72 * dim.getHeight());
	    	
	    	Point end= new Point(p,q);
	    	
	    	ActionBase.doSwipe(driver,start,end,1500);
	    	String percentage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'%')]")).getText();
	    	Assert.assertEquals(percentage, "25%");
	    	
	    	
	      
	    }

	    
	    @Test
	    public void slide75() throws InterruptedException {
	    	Thread.sleep(6000);
	    	Dimension dim = driver.manage().window().getSize();
	    	
	    	int x= (int) (0.5 * dim.getWidth());
	    	int y= (int) (0.72 * dim.getHeight());
	    	
	    	
	    	Point start= new Point(x,y);
	    	int p= (int) (0.6695 * dim.getWidth());
	    	int q= (int) (0.72 * dim.getHeight());
	    	
	    	Point end= new Point(p,q);
	    	
	    	ActionBase.doSwipe(driver,start,end,1500);
	    	String percentage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[contains(@text,'%')]")).getText();
	    	Assert.assertEquals(percentage, "75%");
	    	
	    	
	      
	    }


	    @AfterClass
	    public void afterClass() {
	        driver.quit();
	    }
	

}
