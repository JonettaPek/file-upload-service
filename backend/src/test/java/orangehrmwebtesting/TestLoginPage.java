package orangehrmwebtesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

// Step 1: Find web element
// By.id()
// By.className()
// By.cssSelector()
// By.xpath()
// By.name()
// By.tagName()
// By.linkText()
// By.partialLinkText()
// Step 2: Perform action on web element

public class TestLoginPage {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogin() throws InterruptedException {
        Thread.sleep(2000);
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");
        WebElement login = driver.findElement(By.tagName("button"));
        login.click();

        Thread.sleep(2000);
        String actual = driver.findElement(By.tagName("h6")).getText();
        String expected = "Dashboard";
        Assert.assertEquals(actual, expected);
    }
}
