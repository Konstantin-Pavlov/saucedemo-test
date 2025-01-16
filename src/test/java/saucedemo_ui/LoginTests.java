package saucedemo_ui;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import saucedemo_ui.page.LoginPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("user_login")
public class LoginTests extends BaseSelenideTest {

    private final LoginPage loginPage = page(LoginPage.class);
    private final String wrongFirstName = faker.name().firstName();
    private final String wrongPassword = faker.internet().password();

    @Test
    @Step("Test the successful login with valid credentials")
    public void testSuccessfulLogin() {
        openLoginPage();
        enterCredentials();
        submitLogin();
        verifySuccessfulLogin();
    }

    @Test
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
