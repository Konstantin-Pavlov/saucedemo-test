package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:application.properties")  // Directly load from application.properties
public interface ApplicationConfig extends Config {

    @Key("base_url")
    String baseUrl();

    @Key("inventory_url")
    String inventoryUrl();

    @Key("cart_url")
    String cartUrl();

    @Key("item_to_add")
    String itemToAdd();

    @Key("placing_order_message")
    String placingOrderMessage();

    @Key("user_name")
    String userName();

    @Key("password")
    String password();
}
