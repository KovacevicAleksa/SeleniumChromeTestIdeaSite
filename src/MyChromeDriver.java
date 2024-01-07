import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MyChromeDriver {

    public static void main(String[] args) {
        // Postavite putanju do ChromeDriver izvršnog fajla
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome Dev\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\ChromeDeveloper\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Kreirajte instancu ChromeDrivera
        ChromeDriver driver = new ChromeDriver(options);

        // Otvorite URL u pregledaču
        driver.get("http://www.google.com");

        // Sačekajte nekoliko sekundi
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Zatvorite prozor pregledača
        driver.quit();
    }
}
