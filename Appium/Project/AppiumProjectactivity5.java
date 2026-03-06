package activities;

import static org.testng.Assert.assertEquals;

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

public class AppiumProjectactivity5 {
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
    	driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".scrollTextIntoView(\"Login Form\")")).click();
//)).click();
    	//driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".flingForward()"));
    	//WebElement loginform=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Form\"]")));
    	//loginform.click();
    	WebElement username=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")));
    	username.sendKeys("admin");
    	
    	WebElement password=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")));
    	password.sendKeys("password");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();
    	
        String success=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Welcome Back, Admin!\"]"))).getText();
        
    	assertEquals(success,"Welcome Back, Admin!");
    	System.out.println("login success");
    	driver.get("https://training-support.net/webelements");
    	
    	String UiScrollable1 = "UiScrollable(UiSelector().scrollable(true))";
    	driver.findElement(AppiumBy.androidUIAutomator(UiScrollable1 + ".scrollTextIntoView(\"Login Form\")")).click();
    	
    	WebElement username1=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")));
    	username1.sendKeys("admit");
    	
    	WebElement password1=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")));
    	password1.sendKeys("password1");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();
    	
        String failed=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"subheading\"]"))).getText();
    
    	assertEquals(failed,"Invalid credentials");
    	System.out.println("login failed");
 
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
