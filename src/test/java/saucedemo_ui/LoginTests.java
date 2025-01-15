package saucedemo_ui;


import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import saucedemo_ui.page.LoginPage;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("UI")
@Feature("User Login")
//@ExtendWith({AllureJunit5.class})
public class LoginTests extends BaseSelenideTest {

    private final LoginPage loginPage = page(LoginPage.class);

    @Test
    @Step("Test the successful login with valid credentials")
    public void testSuccessfulLogin() {
        openLoginPage();
        enterCredentials("standard_user", "secret_sauce");
        submitLogin();
        verifySuccessfulLogin();
    }

    private void openLoginPage() {
        LOG.info("Opening the login page.");
        loginPage.openPage();
    }

    private void enterCredentials(String username, String password) {
        LOG.info("Entering credentials: username = {}, password = {}", username, password);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
    }

    private void submitLogin() {
        LOG.info("Submitting the login form.");
        loginPage.clickLogin();
    }

    private void verifySuccessfulLogin() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = com.codeborne.selenide.WebDriverRunner.url();
        LOG.info("Verifying successful login. Expected URL: {}, Actual URL: {}", expectedUrl, actualUrl);
        assertEquals(expectedUrl, actualUrl, "Login was not successful");
    }
}
