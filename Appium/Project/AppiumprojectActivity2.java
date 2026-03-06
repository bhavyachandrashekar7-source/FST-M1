package activities;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import static activities.ActionsBase.longPress;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class AppiumprojectActivity2 {
		

		AndroidDriver driver;
		WebDriverWait wait;
		
		
		@BeforeClass
		public void setUp() throws MalformedURLException, URISyntaxException {
	        // Desired Capabilities
			File appFile = new File("src/test/resources/ts-todo-list-v1.apk");
			
	        UiAutomator2Options options = new UiAutomator2Options();
	        
	        options.setPlatformName("Android");
	        options.setAutomationName("UiAutomator2");
	        options.setApp(appFile.getAbsolutePath());
	        options.noReset();
	        //options.doesEnforceAppInstall();
	        URL serverURL = new URI("http://localhost:4723/").toURL();
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
	    	//driver.findElement(AppiumBy.id("com.app.todolist:id/btn_skip")).click();
	    
	    	WebElement act1= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text=\"task1\"]")));
	    	act1.click();
	    	
	    
            Dimension dims = driver.manage().window().getSize();
            int startx= (int)(0.14*dims.getWidth());
            int starty= (int)(0.11*dims.getHeight());
    		// Set the start and end points
    		Point start = new Point(startx,starty);
    		
    		ActionsBase1.doLongPress(driver,start);
	    	
	    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Edit To-Do Task\"]")).click();
	    	
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/tv_todo_list_deadline")).click();
	    	
	    	driver.findElement(AppiumBy.accessibilityId("30 December 2025")).click();
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/bt_deadline_ok")).click();
	    	driver.findElement(AppiumBy.id("com.app.todolist:id/bt_new_task_ok")).click();
	    	String act3= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_deadline\" and @text=\"Deadline: 30.12.2025\"]"))).getText();
	    	assertEquals(act3,"Deadline: 31.12.2025");
	    	System.out.println("Heading: " + act3);
	    }
	 
	    @AfterClass
	    public void tearDown() {
	        // Close the app
	        driver.quit();
	    }
	    // Create the long press action function
	    
	}




