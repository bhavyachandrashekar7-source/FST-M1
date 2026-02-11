package SeleniumProject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class JOBBoardProject {

    WebDriver driver;
    WebDriverWait wait;

    String baseURL = "https://alchemy.hguy.co/jobs/";
    String username = "root";
    String password = "pa$$w0rd";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // 1. Verify Website Title
    @Test(priority = 1)
    public void verifyWebsiteTitle() {
        driver.get(baseURL);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Alchemy Jobs – Job Board Application");
    }

    // 2. Verify Website Heading
    @Test(priority = 2)
    public void verifyWebsiteHeading() {
        driver.get(baseURL);
        String heading = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(heading, "Welcome to Alchemy Jobs");
    }

    // 3. Get URL of Header Image
    @Test(priority = 3)
    public void getHeaderImageURL() {
        driver.get(baseURL);
        WebElement image = driver.findElement(By.cssSelector("img"));
        String imageURL = image.getAttribute("src");
        System.out.println("Header Image URL: " + imageURL);
    }

    // 4. Verify Second Heading
    @Test(priority = 4)
    public void verifySecondHeading() {
        driver.get(baseURL);
        List<WebElement> headings = driver.findElements(By.tagName("h2"));
        String secondHeading = headings.get(0).getText();
        Assert.assertEquals(secondHeading, "Quia quis non");
    }

    // 5. Navigate to Jobs Page
    @Test(priority = 5)
    public void navigateToJobsPage() {
        driver.get(baseURL);
        driver.findElement(By.linkText("Jobs")).click();
        wait.until(ExpectedConditions.titleContains("Jobs"));
        Assert.assertTrue(driver.getTitle().contains("Jobs"));
    }

    // 6. Apply to a Job
    @Test(priority = 6)
    public void applyToJob() {
        driver.get(baseURL);
        driver.findElement(By.linkText("Jobs")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_keywords")));
        driver.findElement(By.id("search_keywords")).sendKeys("Developer", Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".job_listing")));
        driver.findElements(By.cssSelector(".job_listing")).get(0).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".application_button")));
        driver.findElement(By.cssSelector(".application_button")).click();

        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".application_details"))
        );

        System.out.println("Application Email: " + email.getText());
    }

    // 7. Create Job Listing (Frontend)
    @Test(priority = 7)
    public void createJobListingFrontend() {
        driver.get(baseURL);
        driver.findElement(By.linkText("Post a Job")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_account_email")));
        driver.findElement(By.id("create_account_email")).sendKeys("test@test.com");
        driver.findElement(By.id("job_title")).sendKeys("Automation Tester");
        driver.findElement(By.id("job_location")).sendKeys("Remote");
        driver.findElement(By.id("job_description")).sendKeys("Automation Testing Job Post");

        driver.findElement(By.name("submit_job")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("job_preview_submit_button")));
        driver.findElement(By.id("job_preview_submit_button")).click();

        driver.findElement(By.linkText("Jobs")).click();
        Assert.assertTrue(driver.getPageSource().contains("Automation Tester"));
    }

    // 8. Login to Backend
    @Test(priority = 8)
    public void loginBackend() {
        driver.get(baseURL + "wp-admin");

        driver.findElement(By.id("user_login")).sendKeys(username);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpadminbar")));
        Assert.assertTrue(driver.getPageSource().contains("Dashboard"));
    }

    // 9. Create Job Listing Using Backend
    @Test(priority = 9)
    public void createJobListingBackend() {
        driver.get(baseURL + "wp-admin");

        driver.findElement(By.id("user_login")).sendKeys(username);
        driver.findElement(By.id("user_pass")).sendKeys(password);
        driver.findElement(By.id("wp-submit")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Job Listings")));
        driver.findElement(By.linkText("Job Listings")).click();
        driver.findElement(By.linkText("Add New")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
        driver.findElement(By.id("title")).sendKeys("Backend Automation Job");

        driver.findElement(By.id("publish")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("published"));
    }
}
