package org.example.utils;

import java.time.Duration;

public class EnvConfig {
    public static final String PATH_TO_YANDEX_BROWSER = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";
    public static final String DRIVER_VERSION = "146.0.7680.80";
    //public static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";
    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    public static final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(10);
}
