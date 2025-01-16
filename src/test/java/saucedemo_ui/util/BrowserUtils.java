package saucedemo_ui.util;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtils {
    public static void browserSetup() {
        WebDriverManager.edgedriver().setup();

        Configuration.browser = "edge";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }
}
