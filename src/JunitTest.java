
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;
import java.time.Duration;








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
    public void testRegister() {
    	

        driver.get("https://online.idea.rs/#!/");
        driver.manage().window().setSize(new Dimension(1920, 1040));
        driver.findElement(By.linkText("Registracija")).click();
        w8();
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
          

        
        assertEquals("https://online.idea.rs/#!/account/new", driver.getCurrentUrl());
    }
   
    @Test
    public void TestLogIn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://online.idea.rs/");
        driver.manage().window().setSize(new Dimension(1920, 1040));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Prijava"))).click();
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys("aleksakovacevicc@gmail.com");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("Aleksa123");
        driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
        
        assertEquals("https://online.idea.rs/#!/login", driver.getCurrentUrl());
        
    	
    }

    
    @Test
    public void testLogOut() {
    	
      TestLogIn();
      w8();
      driver.get("https://online.idea.rs/");
      driver.manage().window().setSize(new Dimension(1920, 1040));
    
      driver.findElement(By.linkText("aleksakovacevicc@gmail.com")).click();
      w8();
      driver.findElement(By.cssSelector(".osobni-podaci > a:nth-child(3)")).click();
      
      assertEquals("https://online.idea.rs/#!/account/show?activeTab=orders", driver.getCurrentUrl());

    }
    
    @Test
    public void testUser() {
    	
      TestLogIn();
      w8();
      driver.get("https://online.idea.rs/");
      driver.manage().window().setSize(new Dimension(1920, 1040));
      w8();
      driver.findElement(By.linkText("aleksakovacevicc@gmail.com")).click();
      w8();
      assertEquals("https://online.idea.rs/#!/account/show?activeTab=orders", driver.getCurrentUrl());
      assertEquals(driver.findElement(By.cssSelector(".row:nth-child(1) > .col-md-10 b")).getText(),"Aleksa Kovacevic");
      assertEquals(driver.findElement(By.cssSelector(".row:nth-child(2) > .col-md-10 b")).getText(),"07.12.2001.");
      assertEquals(driver.findElement(By.cssSelector(".row:nth-child(3) b")).getText(),"+381616386025");
      assertEquals(driver.findElement(By.cssSelector("b > span")).getText(),"aleksakovacevicc@gmail.com");


    }
    
    @Test
    public void testShoppingCart() {
      
      TestLogIn();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      w8();
      driver.get("https://online.idea.rs/");
      driver.manage().window().setSize(new Dimension(1920, 1040));
      driver.findElement(By.cssSelector(".input:nth-child(2)")).click();
      w8();
      driver.findElement(By.cssSelector(".input:nth-child(2)")).sendKeys("plazma");
      w8();
      driver.findElement(By.cssSelector(".selected")).click();
      driver.findElement(By.cssSelector(".desktop")).click();

      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .col-lg-2 .add-to-cart > span"))).click();
      driver.findElement(By.cssSelector("div:nth-child(2) > .col-lg-2 .add-to-cart > span")).click();
      driver.findElement(By.cssSelector("div:nth-child(3) > .col-lg-2 .add-to-cart > span")).click();
      w8();
      assertEquals(driver.findElement(By.cssSelector(".total-price")).getText(),"2,264.97 din\n0.00 din");

    }
    
    
    public void w8() {
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    }

}
