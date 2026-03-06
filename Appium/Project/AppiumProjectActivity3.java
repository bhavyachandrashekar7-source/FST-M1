package activities;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AppiumProjectActivity3 {
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
    	
    	driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])[1]")).click();
    	driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"com.app.todolist:id/cb_task_done\"])[2]")).click();
    	WebElement act3= wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/tv_exlv_task_name\" and @text=\"task3\"]")));
    	act3.click();
    	
    
        Dimension dims = driver.manage().window().getSize();
        int startx= (int)(0.19*dims.getWidth());
        int starty= (int)(0.26*dims.getHeight());
		// Set the start and end points
		Point start = new Point(startx,starty);
		
		ActionsBase1.doLongPress(driver,start);
    	
    	driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Edit To-Do Task\"]")).click();
    	driver.findElement(AppiumBy.xpath("//android.widget.SeekBar[@resource-id=\"com.app.todolist:id/sb_new_task_progress\"]")).click();
    	int starta= (int)(0.28*dims.getWidth());
        int startb= (int)(0.53*dims.getHeight());
		// Set the start and end points
		Point star = new Point(starta,startb);
		Point en = new Point((int) (dims.getWidth() * .61), (int) (dims.getHeight() * .54));
		Thread.sleep(9000);
		ActionsBase.doSwipe(driver, star, en, 1500);
		Thread.sleep(9000);
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.app.todolist:id/bt_new_task_ok\"]")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"More options\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.app.todolist:id/title\" and @text=\"Completed tasks\"]"))).click();
    	
    	List<WebElement> count=driver.findElements(AppiumBy.className("android.widget.CheckBox"));
        int count1=count.size();
    	assertEquals(count1,2);
    	System.out.println("total no of tasks: " + count1);
    	
    }
 
 
    

	// Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }




}
