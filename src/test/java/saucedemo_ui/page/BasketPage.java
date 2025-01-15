package saucedemo_ui.page;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class BasketPage {
    public BasketPage openPage() {
        open("https://www.saucedemo.com/cart.html");
        return this;
    }

    @Step("Verify item {itemName} is in the cart")
    public void verifyItemInCart(String itemName) {
        $$(".cart_item").findBy(Condition.text(itemName)).shouldBe(Condition.visible);
    }

}
