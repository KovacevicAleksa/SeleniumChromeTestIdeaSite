
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Keys;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;









public class JunitTest {

	public String info = "";
    private WebDriver driver;
    private String chromeDriverPath = "E:\\Selenium\\ChromeDeveloper\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.err.println("\u001B[37mStarting JUnit-test: \u001B[32m" + methodName);

            
            
            
        }

        @Override
        protected void succeeded(Description description) {
            String methodName = description.getMethodName();
            System.err.println("\u001B[37mTest je uspesno zavrsen. \nNaziv testa: \u001B[32m" + methodName);
            info += "\n"+" Test" +methodName+" je uspesno prosao";
            WriteInFile("test-report.txt", methodName+"Test je uspesno prosao\n");

        }

        @Override
        protected void failed(Throwable e, Description description) {
            String methodName = description.getMethodName();
            System.out.println("\u001B[31m" + methodName + " nije prosao.\u001B[0m");
            info += "\n"+" Test" +methodName+" nije uspesno prosao";
            WriteInFile("test-report.txt", methodName+"Test nije uspesno prosao");


        }
    };

    
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

          String tekstDesc = "[Testiranje registracije]";
          WriteInFile("test-report.txt", tekstDesc);
        
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
        
        String tekstDesc = "[Testira prijave korisnika]";
        WriteInFile("test-report.txt", tekstDesc);
        
        
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
      
      String tekstDesc = "[Testiranje odjavljivanje sa naloga]";
      WriteInFile("test-report.txt", tekstDesc);
      
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
      
      String tekstDesc = "[Testira validacije podataka korisnika]";
      WriteInFile("test-report.txt", tekstDesc);


    }
    
    @Test
    public void testShoppingCart() {
      
      TestLogIn();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      w8();
      driver.get("https://online.idea.rs/");
      driver.manage().window().setSize(new Dimension(1920, 1040));
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".input:nth-child(2)"))).click();

      driver.findElement(By.cssSelector(".input:nth-child(2)")).sendKeys("plazma");
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".selected"))).click();
      driver.findElement(By.cssSelector(".desktop")).click();

      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .col-lg-2 .add-to-cart > span"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > .col-lg-2 .add-to-cart > span"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .col-lg-2 .add-to-cart > span"))).click();

      w8();w8();w8();
      assertEquals(driver.findElement(By.cssSelector(".total-price")).getText(),"2,184.97 din\n0.00 din");
      assertEquals(driver.findElement(By.cssSelector("div:nth-child(1) > .im-slide .akcija")).getText(),"AKCIJA 419.99 din");
      assertEquals(driver.findElement(By.cssSelector("div:nth-child(2) > .im-slide .akcija")).getText(),"1,499.99 din");
      assertEquals(driver.findElement(By.cssSelector("div:nth-child(3) > .im-slide .akcija")).getText(),"264.99 din");
      
      String tekstDesc = "[Testiranje dodavanje artikala u korpi i testiranje krajnju cene]";
      WriteInFile("test-report.txt", tekstDesc);

    }
    

    
    @Test
    public void AvgSpeed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        TestLogIn();
        w8();
        long startTime = System.currentTimeMillis();

        driver.get("https://online.idea.rs/");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".news-icon"))).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action-icon > p"))).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".favorit-icon > p"))).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("aleksakovacevicc@gmail.com"))).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".promjena-podataka-button"))).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("body")));

        long endTime = System.currentTimeMillis();
        long pageLoadTime = endTime - startTime;

        long avg = pageLoadTime/6;
        
        String tekstDesc = "[Testiranje prosecne brzine odziva sajta]";
        WriteInFile("test-report.txt", tekstDesc);
        
        assertTrue(avg<5000); 
           
    }
    

    

    
    @Test
    public void DeleteItem() {
      TestLogIn();
      w8();
      driver.get("https://online.idea.rs/");
      driver.manage().window().setSize(new Dimension(1920, 1040));
      driver.findElement(By.cssSelector(".cart-icon > p")).click();
      w8();
      driver.findElement(By.cssSelector("div:nth-child(1) > .im-slide .delete-cart-item")).click();
      w8();
      driver.findElement(By.cssSelector("div:nth-child(1) > .im-slide .delete-cart-item")).click();
      w8();
      driver.findElement(By.cssSelector(".delete-cart-item")).click();

      w8();
      driver.close();
      
      String tekstDesc = "[Testiranje brisanja artikla iz korpe]";
      WriteInFile("test-report.txt", tekstDesc);
    }
    
    @Test
    public void WriteReport() {
    	driver.get("https://online.idea.rs/");
    	
    	String tekst = "Osvnovni podaci o kompaniji \n"+"---------------------------------"+ "\n"
    	+driver.findElement(By.cssSelector("p:nth-child(8)")).getText() + "\n\n" 
    	+driver.findElement(By.cssSelector("p:nth-child(7)")).getText()+"\n"+"---------------------------------" + "\n";
    	

        WriteInFile("test-report.txt", tekst);
        
        String tekstDesc = "[Testiranje pisanja podataka u fajlu]";
        WriteInFile("test-report.txt", tekstDesc);
        
        assertFalse(tekst.isEmpty());       

    }
    
    
    private void WriteInFile(String putanja, String tekst) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(putanja, true))) {
            writer.write(tekst);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void w8() {
        try {
            Thread.sleep(3000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
    }

}
