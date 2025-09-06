package config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Configuration {
    protected WebDriver driver;

    @Before
    public void installDriver() {
        String browser = System.getProperty("browser", "chrome");//браузер меняется здесь

        if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        acceptCookie();
    }

    private void acceptCookie(){
        try {
            WebElement cookieButton = driver.findElement(By.xpath(".//div[@class='App_CookieConsent__1yUIN']//button"));
            cookieButton.click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOf(cookieButton));
        } catch (Exception e) {
            System.out.println("Баннер закрыт или не найден");
        }
    }




    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}