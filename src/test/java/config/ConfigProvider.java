package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * A utility class for providing access to application configuration values.
 * This class uses the `ConfigFactory` to load an instance of the `ApplicationConfig` interface,
 * which maps configuration properties from the `application.properties` file.
 * The class is designed as a singleton, ensuring that the configuration is loaded only once
 * and can be accessed globally throughout the application.
 * <p>
 * This class has a private constructor to prevent instantiation, and provides a static method
 * to access the configuration values.
 */
public class ConfigProvider {
    // Application configuration instance created using ConfigFactory
    private static final ApplicationConfig CONFIG = ConfigFactory.create(ApplicationConfig.class);

    private ConfigProvider() {
        // Private constructor to prevent instantiation
    }

    /**
     * Provides access to the application configuration.
     *
     * @return the application configuration instance
     */
    public static ApplicationConfig getConfig() {
        return CONFIG;
    }
}
