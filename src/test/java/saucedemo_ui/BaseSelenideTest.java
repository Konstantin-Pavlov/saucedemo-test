package saucedemo_ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import config.ConfigProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * Базовый класс для инициализации селенида
 */
abstract public class BaseSelenideTest {
    protected final static Logger LOG = LogManager.getLogger(BaseSelenideTest.class);
    protected static final Faker faker = new Faker();
    protected static final String BASE_URL = ConfigProvider.getConfig().baseUrl();
    protected static final String INVENTORY_URL = ConfigProvider.getConfig().inventoryUrl();
    protected static final String CART_URL = ConfigProvider.getConfig().cartUrl();
    protected static final String ITEM_TO_ADD = ConfigProvider.getConfig().itemToAdd();
    protected static final String PLACING_ORDER_MESSAGE = ConfigProvider.getConfig().placingOrderMessage();
    protected static final String USER_NAME = ConfigProvider.getConfig().userName();
    protected static final String PASSWORD = ConfigProvider.getConfig().password();

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

        Configuration.browser = "edge";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
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
