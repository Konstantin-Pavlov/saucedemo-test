package saucedemo_ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    // Locators
    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $(".error-message-container");

    // Open the login page
    @Step("Open the login page")
    public LoginPage openPage() {
        open("https://www.saucedemo.com/");
        return this;
    }

    // Set the username
    @Step("Enter username: {username}")
    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    // Set the password
    @Step("Enter password: {password}")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    // Click the login button
    @Step("Click the login button")
    public LoginPage clickLogin() {
        loginButton.click();
        return this;
    }

    // Get the error message
    @Step("Click the login button")
    public String getErrorMessage() {
        return errorMessage.shouldBe(Condition.visible).getText();
    }

    // Perform login
    @Step("Perform login with username: {username} and password: {password}")
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }
}

