package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
        }
    }

    public void startUpChrome() {
        //WebDriverManager.chromiumdriver().browserVersion("148").setup();
        WebDriverManager.chromiumdriver().setup();
        var options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
    }

    public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup();
        var options = new FirefoxOptions();
        options.addArguments("-private");
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
    }

    public void startUpYandex() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.chromiumdriver().driverVersion(DRIVER_VERSION).setup();
        var options = new ChromeOptions();
        options.setBinary(PATH_TO_YANDEX_BROWSER);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
