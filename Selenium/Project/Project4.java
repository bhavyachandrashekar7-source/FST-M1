import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class Project4 {

    WebDriver driver;
    WebDriverWait wait;

    String baseURL = "http://alchemy.hguy.co/orangehrm";
    String username = "orange";
    String password = "orangepassword123";

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // ✅ Reusable Login Method
    public void login() {
        driver.get(baseURL);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtUsername"))).sendKeys(username);
        driver.findElement(By.id("txtPassword")).sendKeys(password);
        driver.findElement(By.id("btnLogin")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));
    }

    // 1️⃣ Verify Website Title
    @Test(priority = 1)
    public void verifyTitle() {
        driver.get(baseURL);
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }

    // 2️⃣ Get Header Image URL
    @Test(priority = 2)
    public void getHeaderImageURL() {
        driver.get(baseURL);
        WebElement logo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='divLogo']//img"))
        );
        System.out.println("Header Image URL: " + logo.getAttribute("src"));
    }

    // 3️⃣ Login Test
    @Test(priority = 3)
    public void loginTest() {
        login();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    // 4️⃣ Add Employee
    @Test(priority = 4)
    public void addEmployee() {
        login();

        driver.findElement(By.id("menu_pim_viewPimModule")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_addEmployee"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName"))).sendKeys("John");
        driver.findElement(By.id("lastName")).sendKeys("Doe");

        driver.findElement(By.id("btnSave")).click();

        // Verify Employee List
        driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
        Assert.assertTrue(driver.getPageSource().contains("John"));
    }

    // 5️⃣ Edit User Information
    @Test(priority = 5)
    public void editUserInfo() {
        login();

        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click(); // Edit

        WebElement firstName = driver.findElement(By.id("personal_txtEmpFirstName"));
        firstName.clear();
        firstName.sendKeys("Orange");

        driver.findElement(By.id("personal_optGender_1")).click();

        Select nationality = new Select(driver.findElement(By.id("personal_cmbNation")));
        nationality.selectByIndex(5);

        WebElement dob = driver.findElement(By.id("personal_DOB"));
        dob.clear();
        dob.sendKeys("1990-01-01");

        driver.findElement(By.id("btnSave")).click();
    }

    // 6️⃣ Verify Directory Menu
    @Test(priority = 6)
    public void verifyDirectoryMenu() {
        login();

        WebElement directory = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("menu_directory_viewDirectory"))
        );

        Assert.assertTrue(directory.isDisplayed());
        Assert.assertTrue(directory.isEnabled());

        directory.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Search Directory");
    }

    // 7️⃣ Add Qualification
    @Test(priority = 7)
    public void addQualification() {
        login();

        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Qualifications"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("addWorkExperience"))).click();

        driver.findElement(By.id("experience_employer")).sendKeys("ABC Company");
        driver.findElement(By.id("experience_jobtitle")).sendKeys("Tester");

        driver.findElement(By.id("btnWorkExpSave")).click();
    }

    // 8️⃣ Apply Leave
    @Test(priority = 8)
    public void applyLeave() {
        login();

        driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_applyLeave"))).click();

        Select leaveType = new Select(driver.findElement(By.id("applyleave_txtLeaveType")));
        leaveType.selectByIndex(1);

        WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
        fromDate.clear();
        fromDate.sendKeys("2024-12-01");

        WebElement toDate = driver.findElement(By.id("applyleave_txtToDate"));
        toDate.clear();
        toDate.sendKeys("2024-12-02");

        driver.findElement(By.id("applyBtn")).click();

        driver.findElement(By.id("menu_leave_viewMyLeaveList")).click();
        Assert.assertTrue(driver.getPageSource().contains("Pending Approval"));
    }

    // 9️⃣ Retrieve Emergency Contacts
    @Test(priority = 9)
    public void retrieveEmergencyContacts() {
        login();

        driver.findElement(By.id("menu_pim_viewMyDetails")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Emergency Contacts"))).click();

        List<WebElement> rows = driver.findElements(By.cssSelector("#emgcontact_list tbody tr"));

        System.out.println("Emergency Contacts:");

        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + " | ");
            }
            System.out.println();
        }
    }
}
