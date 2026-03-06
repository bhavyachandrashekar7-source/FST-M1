package activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import static activities.ActionsBase.tap;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumProjectActivity6 {
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
        wait= new WebDriverWait(driver, Duration.ofSeconds(30));
        //driver.get("https://training-support.net");)
    }
 
    // Test method
    @Test
    public void Test() throws InterruptedException {
        // Perform the calculation
    	driver.get("https://training-support.net/webelements");
    	String UiScrollable = "UiScrollable(UiSelector().scrollable(true))";
    	driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".scrollForward(24)"));
    	//driver.findElement(AppiumBy.androidUIAutomator(UiScrollable + ".scrollIntoView("new UiSelector().text(\"Popups\")")")).click();"
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Popups\"]")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"launcher\"]"))).click();
    	//Alert promptAlert = driver.switchTo().alert();
    	Dimension dims = driver.manage().window().getSize();
    	Point start = new Point((int) (dims.getWidth() * 0.5), (int) (dims.getHeight() * 0.27));
        ActionsBase1.doLongPress(driver, start);
		
    	WebElement username=driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"username\"]"));
    	username.sendKeys("admin");
    	
    	WebElement password=driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"));
    	password.sendKeys("password");
    	driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Submit\"]")).click();
    	
        String success=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Welcome Back, Admin!\"]"))).getText();
        
    	assertEquals(success,"Welcome Back, Admin!");
    	System.out.println("login success");
    	
    }
 
 
    

	// Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }




}
