package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.utils.EnvConfig.DRIVER_VERSION;
import static org.example.utils.EnvConfig.PATH_TO_YANDEX_BROWSER;

public class DriverFactory {
    private WebDriver driver;

    public void startUp() {
        if ("firefox".equals(System.getProperty("browser"))) {
            startUpFirefox();
        } else if ("yandex".equals(System.getProperty("browser"))) {
            startUpYandex();
        } else {
            startUpChrome();
            //startUpYandex();
        }
    }

    public void startUpChrome() {
        //WebDriverManager.chromiumdriver().browserVersion("148").setup();
        WebDriverManager.chromiumdriver().setup();
        driver = new ChromeDriver();
    }

    public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public void startUpYandex() {
        WebDriverManager.chromedriver().driverVersion(DRIVER_VERSION).setup();
        //WebDriverManager.chromedriver().setup();

        var options = new ChromeOptions();
        options.setBinary(PATH_TO_YANDEX_BROWSER);

        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
