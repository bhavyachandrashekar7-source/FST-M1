package Activities;


import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.SupportsContextSwitching;

public class Activity5  extends Gesture
{
	//Declaration of Objects
	AppiumDriver driver;
	WebDriverWait wait;
	
	
	//setUp function
	@BeforeClass
	public void Setup() throws MalformedURLException, URISyntaxException
	{
	//Desired Capabalities
		// Desired Capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.setAppPackage("com.android.chrome");
		options.setAppActivity("com.google.android.apps.chrome.Main");
		options.noReset();
		options.setCapability("chromedriverAutoDownload", true);
		// Server Address
		URL serverURL = new URI("http://localhost:4723").toURL();
		// Driver Initialization
		driver = new AndroidDriver(serverURL, options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		//Open the page
		driver.get("https://training-support.net/webelements");
	}
	@Test
	public void testMethod() throws InterruptedException
	{
		Dimension dims = driver.manage().window().getSize();
        System.out.println(dims);
        Point start = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.8));
        Point end = new Point((int)(dims.getWidth() * 0.5), (int)(dims.getHeight() * 0.5));

        // Wait for page to load
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(AppiumBy.xpath("//android.widget.TextView[@text='WebElements']")));

        // Scroll(Fling) to the end of the page
        doSwipe(driver, start, end, 150);
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Login Form\"]")).click();
//        wait.until(ExpectedConditions.visibilityOf((WebElement) AppiumBy.id("username")));
        Thread.sleep(2000);
        driver.findElement(AppiumBy.id("username")).sendKeys("admin");
  
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}