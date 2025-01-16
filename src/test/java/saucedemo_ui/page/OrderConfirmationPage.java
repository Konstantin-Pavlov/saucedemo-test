package saucedemo_ui.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page object model for the Order Confirmation page.
 * This class provides methods to interact with the Order Confirmation page, specifically
 * to retrieve the confirmation message that appears after an order has been successfully placed.
 */
public class OrderConfirmationPage {

    /**
     * Gets the confirmation message displayed on the page after a successful order.
     *
     * @return SelenideElement representing the confirmation message.
     */
    public SelenideElement getConfirmationMessage() {
        return $(".complete-header");
    }
}
