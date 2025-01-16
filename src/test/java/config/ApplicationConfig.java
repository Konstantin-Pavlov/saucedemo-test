package config;

import org.aeonbits.owner.Config;

/**
 * Configuration interface for loading application-specific properties from the classpath.
 * This interface is used to map properties defined in the `application.properties` file.
 * It provides access to various application settings such as URLs, login credentials,
 * and other parameters related to the application's behavior.
 * <p>
 * This interface uses the `@Config.Sources` annotation to specify the source of the configuration file
 * and the `@Key` annotations to define the keys for each configuration value.
 */
@Config.Sources("classpath:application.properties")  // Directly load from application.properties
public interface ApplicationConfig extends Config {

    /**
     * Gets the base URL for the application.
     *
     * @return the base URL
     */
    @Key("base_url")
    String baseUrl();

    /**
     * Gets the URL for the inventory page.
     *
     * @return the inventory page URL
     */
    @Key("inventory_url")
    String inventoryUrl();

    /**
     * Gets the URL for the shopping cart page.
     *
     * @return the cart page URL
     */
    @Key("cart_url")
    String cartUrl();

    /**
     * Gets the name of the item to be added to the basket.
     *
     * @return the name of the item to add
     */
    @Key("item_to_add")
    String itemToAdd();

    /**
     * Gets the message displayed when an order is being placed.
     *
     * @return the order placing message
     */
    @Key("placing_order_message")
    String placingOrderMessage();

    /**
     * Gets the message displayed when a login attempt fails.
     *
     * @return the login failed message
     */
    @Key("login_failed_message")
    String loginFailedMessage();

    /**
     * Gets the username used for login.
     *
     * @return the username
     */
    @Key("user_name")
    String userName();

    /**
     * Gets the password used for login.
     *
     * @return the password
     */
    @Key("password")
    String password();
}
