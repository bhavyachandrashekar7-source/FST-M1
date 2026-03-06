package Activities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity1 {
	
	    WebDriverWait wait;
	    AndroidDriver driver;

	    // Set up method
	    @BeforeClass
	    public void setUp() throws MalformedURLException, URISyntaxException {
	        // Desired Capabilities
	    	File appFile = new File("src/test/resources/calculator.apk");
	        UiAutomator2Options options = new UiAutomator2Options();
	        options.setPlatformName("android");
	        options.setAutomationName("UiAutomator2");
	        options.setApp(appFile.getAbsolutePath());
	        options.noReset();

	        // Server URL
	        URL serverURL = new URI("http://127.0.0.1:4723").toURL();

	        // Driver initialization
	        driver = new AndroidDriver(serverURL, options);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Open the page in browser
	       // driver.get("https://www.training-support.net/selenium/target-practice");
	    }

	    @Test
	    public void listElements() {
	    	driver.findElement(AppiumBy.id("digit_8")).click();
	    	driver.findElement(AppiumBy.accessibilityId("multiply")).click();
	    	driver.findElement(AppiumBy.id("digit_6")).click();
	    	
	    	driver.findElement(AppiumBy.accessibilityId("equals")).click();
	    	String result = driver.findElement(AppiumBy.id("result_final")).getText();
	    	Assert.assertEquals(result, "48");
	    	
	        // Wait for elements to load
	    }

	    @AfterClass
	    public void afterClass() {
	        driver.quit();
	    }
	

}
