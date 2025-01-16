package saucedemo_ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import config.ConfigProvider;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import saucedemo_ui.util.BrowserUtils;


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
    protected static final String LOGIN_FAILED_MESSAGE = ConfigProvider.getConfig().loginFailedMessage();
    protected static final String USER_NAME = ConfigProvider.getConfig().userName();
    protected static final String PASSWORD = ConfigProvider.getConfig().password();

    /**
     * Инициализация selenide с настройками
     */
    public void setUp() {
        BrowserUtils.browserSetup();

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
    @Step("Closing webdriver")
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
