package saucedemo_ui;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import saucedemo_ui.page.BasketPage;
import saucedemo_ui.page.CheckoutPage;
import saucedemo_ui.page.InventoryPage;
import saucedemo_ui.page.LoginPage;
import saucedemo_ui.page.OrderConfirmationPage;

import static com.codeborne.selenide.Selenide.page;

@Epic("UI")
@Feature("Order Processing")
public class OrderTests extends BaseSelenideTest {
    private final InventoryPage inventoryPage = page(InventoryPage.class);
    private final BasketPage basketPage = page(BasketPage.class);
    private final CheckoutPage checkoutPage = page(CheckoutPage.class);
    private final OrderConfirmationPage orderConfirmationPage = page(OrderConfirmationPage.class);
    private final String firstName = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final String zipCode = faker.address().zipCode();

    @Test
    public void testAddItemToBasketAndCompleteOrder() {
        LOG.info("Starting test: Add item to basket and complete order.");
        loginAsStandardUser();
        addItemToBasket();
        verifyItemInBasket();
        proceedToCheckout();
        fillCheckoutInformation();
        completeOrder();
        verifyOrderCompletion();
    }

    @Step("Log in as standard user")
    private void loginAsStandardUser() {
        LOG.info("Opening login page and logging in as standard user.");
        LoginPage loginPage = page(LoginPage.class);
        loginPage.openPage(BASE_URL)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .clickLogin();
        LOG.info("User logged in successfully.");
    }

    @Step("Add item {itemName} to basket")
    private void addItemToBasket() {
        LOG.info("Adding item '{}' to the basket.", ITEM_TO_ADD);
        inventoryPage.addItemToBasket(ITEM_TO_ADD);
        LOG.info("Item '{}' added to the basket.", ITEM_TO_ADD);
    }

    @Step("Verify item {itemName} is in the basket")
    private void verifyItemInBasket() {
        LOG.info("Verifying that item '{}' is in the basket.", ITEM_TO_ADD);
        basketPage.openPage(CART_URL).verifyItemInCart(ITEM_TO_ADD);
        LOG.info("Item '{}' is in the basket.", ITEM_TO_ADD);
    }

    @Step("Proceed to checkout")
    private void proceedToCheckout() {
        LOG.info("Proceeding to checkout.");
        basketPage.clickCheckout();
        LOG.info("Proceeded to checkout.");
    }

    @Step("Fill checkout information: {firstName}, {lastName}, {postalCode}")
    private void fillCheckoutInformation() {


        LOG.info("Filling out checkout information: {} {} with postal code: {}", firstName, lastName, zipCode);
        checkoutPage.fillInCheckoutInfo(firstName, lastName, zipCode)
                .clickContinue();
        LOG.info("Checkout information filled successfully.");
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
                .shouldHave(Condition.text(PLACING_ORDER_MESSAGE));
        LOG.info("Order completion verified successfully.");
    }

}
