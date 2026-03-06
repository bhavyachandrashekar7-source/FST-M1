package activities;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumProjectActivity4 {
	AndroidDriver driver;
	WebDriverWait wait;
	
	
	@BeforeClass
	public void setUp() throws MalformedURLException, URISyntaxException {
        // Desired Capabilities
		
        UiAutomator2Options options = new UiAutomator2Options();
        
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();
 
        // Server Address
        URL serverURL = new URI("http://localhost:4723").toURL();
 
        // Driver Initialization
        driver = new AndroidDriver(serverURL, options);
        wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        //driver.get("https://training-support.net");)
    }
 
    // Test method
    @Test
    public void Test() {
        // Perform the calculation
    	driver.get("https://training-support.net/webelements");
    	String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
    	driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".flingForward()"));
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"To-Do List\"]")).click();
    	WebElement firsttask=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]")));
    	firsttask.sendKeys("add task to list");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();
    	WebElement sectask=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]")));
    	sectask.sendKeys("get number of task");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();
    	WebElement thirdtask=driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"todo-input\"]"));
    	thirdtask.sendKeys("clear the list");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"todo-add\"]")).click();
        // Find the result
  
        driver.findElement(AppiumBy.xpath("//android.widget.ListView/android.view.View[3]/android.view.View/android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.ListView/android.view.View[4]/android.view.View/android.widget.CheckBox")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.ListView/android.view.View[5]/android.view.View/android.widget.CheckBox")).click();
        List<WebElement> count=driver.findElements(AppiumBy.className("android.widget.CheckBox"));
        int count1=count.size();
    	assertEquals(count1,5);
    	System.out.println("total no of tasks: " + count1);
 
        // Assertion
        //Assert.assertEquals(result, "");
    }
 
 
    

	// Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }

}
