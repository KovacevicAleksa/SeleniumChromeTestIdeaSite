
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class JunitTest {

    private WebDriver driver;
    private String chromeDriverPath = "E:\\Selenium\\ChromeDeveloper\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome Dev\\Application\\chrome.exe");
        driver = new ChromeDriver(options);
        
    }
    

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testOpenGoogle() {
    	

        // Navigate to Google afterward
        driver.get("https://online.idea.rs/#!/");
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.linkText("Registracija")).click();
        try {
            Thread.sleep(2000); // Čekanje u milisekundama
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("firstName")).click();
        driver.findElement(By.id("firstName")).sendKeys("Aleksa");
        driver.findElement(By.id("lastName")).sendKeys("Kovacevic");
        driver.findElement(By.id("gsmNumber")).sendKeys("+381616386025");
        driver.findElement(By.id("birthDay")).click();
        
        {
            WebElement dropdown = driver.findElement(By.id("birthDay"));
            dropdown.findElement(By.xpath("//option[. = '7']")).click();
          }
          driver.findElement(By.id("birthMonth")).click();
          {
            WebElement dropdown = driver.findElement(By.id("birthMonth"));
            dropdown.findElement(By.xpath("//option[. = 'decembar']")).click();
          }
          driver.findElement(By.id("birthYear")).click();
          {
            WebElement dropdown = driver.findElement(By.id("birthYear"));
            dropdown.findElement(By.xpath("//option[. = '2001']")).click();
          }
          
          driver.findElement(By.cssSelector("span:nth-child(1) > label")).click();
          driver.findElement(By.id("email")).click();
          driver.findElement(By.id("email")).sendKeys("aleksakovacevicc@gmail.com");
          driver.findElement(By.id("emailConfirmation")).sendKeys("aleksakovacevicc@gmail.com");
          driver.findElement(By.id("password")).click();
          driver.findElement(By.id("password")).sendKeys("Aleksa123");
          driver.findElement(By.id("passwordConfirmation")).sendKeys("Aleksa123");
          driver.findElement(By.id("shippingCityNames")).click();
          {
            WebElement dropdown = driver.findElement(By.id("shippingCityNames"));
            dropdown.findElement(By.xpath("//option[. = 'ČAčAK']")).click();
          }
          driver.findElement(By.id("streetName")).click();
          driver.findElement(By.id("streetName")).sendKeys("Svetog Save");
          driver.findElement(By.id("houseNumber")).click();
          driver.findElement(By.id("houseNumber")).sendKeys("66");
          driver.findElement(By.cssSelector(".has-error label")).click();
          driver.findElement(By.name("submit")).click();
          

        // Check the current URL after both navigations
        assertEquals("https://online.idea.rs/#!/account/new", driver.getCurrentUrl());
    }


}
