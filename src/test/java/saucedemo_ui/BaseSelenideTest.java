package saucedemo_ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.chrome.ChromeOptions;


/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest {
    protected final static Logger LOG = LogManager.getLogger(BaseSelenideTest.class);

    /**
     * Инициализация selenide с настройками
     */
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-notifications");

//        Configuration.browser = "chrome";
//        Configuration.driverManagerEnabled = true;
//        Configuration.browserSize = "1920x1080";
//        Configuration.headless = false;

        Configuration.browser = "edge";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";             // Set the browser window size
        Configuration.headless = false;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));
    }

    /**
     * Выполнение метода перед каждым запуском тестов
     */
    @BeforeEach
    public void init() {
        setUp();
    }

    /**
     * Выполнение метода после каждого закрытия тестов
     */
    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
