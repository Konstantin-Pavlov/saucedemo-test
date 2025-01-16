package saucedemo_ui.util;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Utility class for setting up the browser configuration for automated tests.
 * This class provides a method to configure the browser settings such as browser type,
 * window size, and headless mode. It uses WebDriverManager to automatically set up the
 * browser driver and configure the desired browser for the tests.
 * <p>
 * Currently, the setup is for the Edge browser.
 */
public class BrowserUtils {
    public static void browserSetup() {
        WebDriverManager.edgedriver().setup();

        Configuration.browser = "edge";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }
}
