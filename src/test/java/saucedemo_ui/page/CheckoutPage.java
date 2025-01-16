package saucedemo_ui.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

/**
 * Page object model for the Checkout page.
 * This class provides methods to interact with the elements of the Checkout page,
 * such as filling in personal information, and clicking the continue and finish buttons.
 * It uses Selenide to interact with form fields and buttons for submitting checkout details.
 */
public class CheckoutPage {
    public CheckoutPage fillInCheckoutInfo(String firstName, String lastName, String postalCode) {
        $("#first-name").shouldBe(Condition.visible).sendKeys(firstName);
        $("#last-name").shouldBe(Condition.visible).sendKeys(lastName);
        $("#postal-code").shouldBe(Condition.visible).sendKeys(postalCode);
        return this;
    }

    public void clickContinue() {
        $("#continue").click();
    }

    public void clickFinish() {
        $("#finish").click();
    }

}
