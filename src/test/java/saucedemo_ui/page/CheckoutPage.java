package saucedemo_ui.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;

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
