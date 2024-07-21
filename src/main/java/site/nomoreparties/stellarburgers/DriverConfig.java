package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverConfig {
    public static final long TIMEOUT = 15;

    @Step("Подготовка драйвера")
    public static WebDriver setDriver() {
        String browsertype = System.getProperty("browser", "chrome").toLowerCase();

        switch (browsertype) {
            case "chrome":
                return setUpChromeDriver();
            case "firefox":
                return setUpFirefoxDriver();
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер " + browsertype);
        }
    }

    private static WebDriver setUpChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver setUpFirefoxDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //firefoxOptions.addArguments("--headless");
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptions);
    }
}
