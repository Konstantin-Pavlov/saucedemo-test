package saucedemo_ui.page;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Page object model for the Basket (Shopping Cart) page.
 * This class contains methods to interact with and verify elements on the Basket page,
 * such as verifying items in the cart and navigating to the checkout page.
 * It utilizes Selenide to interact with web elements and perform actions like
 * verifying item visibility and clicking the checkout button.
 */
public class BasketPage {
    public BasketPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Verify item {itemName} is in the cart")
    public void verifyItemInCart(String itemName) {
        $$(".cart_item").findBy(Condition.text(itemName)).shouldBe(Condition.visible);
    }

    public void clickCheckout() {
        $("#checkout").click(ClickOptions.usingJavaScript());
    }

}
