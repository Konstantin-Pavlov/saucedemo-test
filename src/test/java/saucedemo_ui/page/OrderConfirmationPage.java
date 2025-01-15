package saucedemo_ui.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class OrderConfirmationPage {
    public SelenideElement getConfirmationMessage() {
        return $(".complete-header");
    }
}
