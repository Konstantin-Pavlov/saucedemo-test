package saucedemo_ui.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {
    public void addItemToBasket(String itemName) {
        $$(".inventory_item").findBy(Condition.text(itemName))
                .$(".btn_inventory").click();
    }
}
