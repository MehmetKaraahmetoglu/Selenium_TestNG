package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    /*
    JUnit'de WebDriver objesi TestBase'den geliyordu
    TestNG extends ile baglanma zorunlulugunu ortadan kaldirmak
    ve testi yazanlara daha fazla kontrol imkani vermek icin
    TestBase yerine driver class'inda static iki method ile
    driver olusturma ve kapatma islemlerini yapmayi tercih etmistir
     */
    static WebDriver driver; //biz deger atamadigimiz icin Java default olarak null point eder

    private Driver() {
        // Bu constructor default constructor ile ayni islevi yapan parametresiz constructor'dir.
        // bune erisimi kontrol edebilecegimiz icin bu constructor'i olusturduk.
        // Private yapinca baska classlardan ulasim olmaz.
        // Page Object Model sistemi oldugu icin utilities altinda ki Driver classini kullanmamiz beklenir.
    }

    public static WebDriver getDriver() {

        String browser = ConfigReader.getProperty("browser");

        if (driver == null) {

            switch (browser) {
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
            }


        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
