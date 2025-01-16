package saucedemo_ui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import saucedemo_ui.page.LoginPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for verifying login functionality.
 * This class contains tests for both successful and failed login attempts using valid and invalid credentials.
 * It uses the LoginPage for interacting with the login form and verifying the login outcome.
 */
@Tag("user_login")
public class LoginTests extends BaseSelenideTest {

    private final LoginPage loginPage = page(LoginPage.class);
    private final String wrongFirstName = faker.name().firstName();
    private final String wrongPassword = faker.internet().password();

    /**
     * Tests the successful login with valid credentials.
     * This test simulates the process of logging in with valid credentials and verifying the successful login
     * by checking the URL after login.
     */
    @Test
    @DisplayName("Test the successful login with valid credentials")
    @Step("Test the successful login with valid credentials")
    public void testSuccessfulLogin() {
        openLoginPage();
        enterCredentials();
        submitLogin();
        verifySuccessfulLogin();
    }

    /**
     * Tests the failed login with invalid credentials.
     * This test simulates the process of logging in with invalid credentials and verifies the error message
     * displayed on the login page.
     */
    @Test
    @DisplayName("Test the failed login with invalid credentials")
    @Step("Test the failed login with invalid credentials")
    public void testFailedLogin() {
        openLoginPage();
        enterWrongCredentials();
        submitLogin();
        verifyFailedLogin();
    }

    private void openLoginPage() {
        LOG.info("Opening the login page.");
        loginPage.openPage(BASE_URL);
    }

    private void enterCredentials() {
        LOG.info("Entering credentials: username = {}, password = {}", USER_NAME, PASSWORD);
        loginPage.setUsername(USER_NAME);
        loginPage.setPassword(PASSWORD);
    }

    private void enterWrongCredentials() {
        LOG.info("Entering wrong credentials: username = {}, password = {}", wrongFirstName, wrongPassword);
        loginPage.setUsername(wrongFirstName);
        loginPage.setPassword(wrongPassword);
    }

    private void submitLogin() {
        LOG.info("Submitting the login form.");
        loginPage.clickLogin();
    }

    private void verifySuccessfulLogin() {
        String expectedUrl = INVENTORY_URL;
        String actualUrl = com.codeborne.selenide.WebDriverRunner.url();
        LOG.info("Verifying successful login. Expected URL: {}, Actual URL: {}", expectedUrl, actualUrl);
        assertEquals(expectedUrl, actualUrl, "Login was not successful");
    }

    private void verifyFailedLogin() {
        String expectedErrorMessage = LOGIN_FAILED_MESSAGE; // Example message
        String actualErrorMessage = loginPage.getErrorMessage();
        LOG.info("Verifying failed login. Expected error message: {}, Actual error message: {}", expectedErrorMessage, actualErrorMessage);
        assertEquals(expectedErrorMessage, actualErrorMessage, "Error message does not match the expected value");
    }
}
