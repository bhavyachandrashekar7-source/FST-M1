package activities;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

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

public class AppiumProjectActivity1 {
	

		AndroidDriver driver;
		WebDriverWait wait;
		
		
		@BeforeClass
		public void setUp() throws MalformedURLException, URISyntaxException {
	        // Desired Capabilities
			File appFile = new File("src/test/resources/ts-todo-list-v1.apk");
			
	        UiAutomator2Options options = new UiAutomator2Options();
	        
	        options.setPlatformName("android");
	        options.setAutomationName("UiAutomator2");
	        options.setApp(appFile.getAbsolutePath());
	        //options.setAppPackage("com.app.todolist");
			//options.setAppActivity(".view.MainActivity");
	        //options.noReset();
	        //options.doesEnforceAppInstall();
	        URL serverURL = new URI("http://localhost:4723").toURL();
	        driver = new AndroidDriver(serverURL,options);
	        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	        //driver.get("https://training-support.net");
	    }
	 
	    // Test method
	    @Test
	    public void Test() {
	        // Perform the calculation
	    	//WebElement allow= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")));
	    	//allow.click();
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.todolist:id/btn_skip"))).click();
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/fab_new_task")).click();
	    	WebElement newtask= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.todolist:id/et_new_task_name")));
	    	newtask.sendKeys("complete activity 1 priority high");
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
	    	String act1= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text=\"complete activity 1 priority high\"]"))).getText();
	    	assertEquals(act1,"complete activity 1 priority high");
	    	System.out.println("Heading: " + act1);
	    	
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/fab_new_task")).click();
	    	WebElement sectask=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.todolist:id/et_new_task_name")));
	    	sectask.sendKeys("complete activity2 with prioritymedium");
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
	    	String act2= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text=\"complete activity2 with prioritymedium\"]"))).getText();
	    	assertEquals(act2,"complete activity2 with prioritymedium");
	    	System.out.println("Heading: " + act2);
	    	
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/fab_new_task")).click();
	    	WebElement thirtask=wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.todolist:id/et_new_task_name")));
	    	thirtask.sendKeys("complete activity3 with prioritylow");
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
	    	String act3= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text=\"complete activity3 with prioritylow\"]"))).getText();
	    	assertEquals(act3,"complete activity3 with prioritylow");
	    	System.out.println("Heading: " + act3);
	    }
	 
	    @AfterClass
	    public void tearDown() {
	        // Close the app
	        driver.quit();
	    }

	}



