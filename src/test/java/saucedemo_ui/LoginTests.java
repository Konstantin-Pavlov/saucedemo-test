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

    @Test
    @Step("Test the successful login with valid credentials")
    public void testSuccessfulLogin() {
        openLoginPage();
        enterCredentials();
        submitLogin();
        verifySuccessfulLogin();
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
}
