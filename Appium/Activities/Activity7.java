package Activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity7 {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("android");
        options.setAutomationName("UiAutomator2");
        options.setAppPackage("com.android.chrome");
        options.setAppActivity("com.google.android.apps.chrome.Main");
        options.noReset();

        URL serverURL = new URI("http://localhost:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://training-support.net/webelements/lazy-loading");
    }

    @Test
    public void uiScrollableTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.className("android.widget.Image")
        ));

        List<WebElement> imageElements =
                driver.findElements(AppiumBy.className("android.widget.Image"));

        System.out.println("Before scroll: " + imageElements.size());

        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".setAsVerticalList()" +
                        ".scrollIntoView(new UiSelector().text(\"Helen\"))"
                )
        );

        imageElements =
                driver.findElements(AppiumBy.className("android.widget.Image"));

        System.out.println("After scroll: " + imageElements.size());

        Assert.assertEquals(imageElements.size(), 3);   
        }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
