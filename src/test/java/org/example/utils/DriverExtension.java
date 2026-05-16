package org.example.utils;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class DriverExtension implements BeforeEachCallback, AfterEachCallback {
    private final DriverFactory factory = new DriverFactory();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        factory.startUp();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        WebDriver driver = factory.getDriver();

        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return factory.getDriver();
    }
}
