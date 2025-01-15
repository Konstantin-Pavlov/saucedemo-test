package saucedemo_ui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import saucedemo_ui.page.BasketPage;
import saucedemo_ui.page.CheckoutPage;
import saucedemo_ui.page.InventoryPage;
import saucedemo_ui.page.LoginPage;
import saucedemo_ui.page.OrderConfirmationPage;
import saucedemo_ui.util.BrowserUtils;

import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

@Epic("UI")
@Feature("Order Processing")
public class OrderTests extends BaseSelenideTest{
    private final InventoryPage inventoryPage = page(InventoryPage.class);
    private final BasketPage basketPage = page(BasketPage.class);
    private final CheckoutPage checkoutPage = page(CheckoutPage.class);
    private final OrderConfirmationPage orderConfirmationPage = page(OrderConfirmationPage.class);

    @Test
    public void testAddItemToBasketAndCompleteOrder() {
        LOG.info("Starting test: Add item to basket and complete order.");
        loginAsStandardUser();
        addItemToBasket("Sauce Labs Backpack");
        verifyItemInBasket("Sauce Labs Backpack");
        proceedToCheckout();
        fillCheckoutInformation("John", "Doe", "12345");
        completeOrder();
        verifyOrderCompletion();
    }

    @Step("Log in as standard user")
    private void loginAsStandardUser() {
        LOG.info("Opening login page and logging in as standard user.");
        LoginPage loginPage = page(LoginPage.class);
        loginPage.openPage()
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        LOG.info("User logged in successfully.");
    }

    @Step("Add item {itemName} to basket")
    private void addItemToBasket(String itemName) {
        LOG.info("Adding item '{}' to the basket.", itemName);
        inventoryPage.addItemToBasket(itemName);
        LOG.info("Item '{}' added to the basket.", itemName);
    }

    @Step("Verify item {itemName} is in the basket")
    private void verifyItemInBasket(String itemName) {
        LOG.info("Verifying that item '{}' is in the basket.", itemName);
        basketPage.openPage().verifyItemInCart(itemName);
        LOG.info("Item '{}' is in the basket.", itemName);
    }

    @Step("Proceed to checkout")
    private void proceedToCheckout() {
        LOG.info("Proceeding to checkout.");
        basketPage.clickCheckout();
        LOG.info("Proceeded to checkout.");
    }

    @Step("Fill checkout information: {firstName}, {lastName}, {postalCode}")
    private void fillCheckoutInformation(String firstName, String lastName, String postalCode) {
        LOG.info("Filling out checkout information: {} {} with postal code: {}", firstName, lastName, postalCode);
        checkoutPage.fillInCheckoutInfo(firstName, lastName, postalCode)
                .clickContinue();
        LOG.info("Checkout information filled successfully.");
        sleep(1000);
    }

    @Step("Complete the order")
    private void completeOrder() {
        LOG.info("Completing the order.");
        checkoutPage.clickFinish();
        LOG.info("Order completed successfully.");
    }

    @Step("Verify order completion")
    private void verifyOrderCompletion() {
        LOG.info("Verifying order completion.");
        orderConfirmationPage.getConfirmationMessage()
                .shouldHave(Condition.text("Thank you for your order!"));
        LOG.info("Order completion verified successfully.");
        sleep(3000);
    }

}
