package saucedemo_ui;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import saucedemo_ui.page.BasketPage;
import saucedemo_ui.page.InventoryPage;
import saucedemo_ui.page.LoginPage;

import static com.codeborne.selenide.Selenide.page;

@Epic("UI")
@Feature("Order Processing")
public class OrderTests {
    private final InventoryPage inventoryPage = page(InventoryPage.class);
    private final BasketPage basketPage = page(BasketPage.class);


    @Test
    public void testAddItemToBasketAndCompleteOrder() {
        loginAsStandardUser();
        addItemToBasket("Sauce Labs Backpack");
        verifyItemInBasket("Sauce Labs Backpack");
    }

    @Step("Log in as standard user")
    private void loginAsStandardUser() {
        LoginPage loginPage = page(LoginPage.class);
        loginPage.openPage()
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
    }

    @Step("Add item {itemName} to basket")
    private void addItemToBasket(String itemName) {
        inventoryPage.addItemToBasket(itemName);
    }

    @Step("Verify item {itemName} is in the basket")
    private void verifyItemInBasket(String itemName) {
        basketPage.openPage().verifyItemInCart(itemName);
    }

}
