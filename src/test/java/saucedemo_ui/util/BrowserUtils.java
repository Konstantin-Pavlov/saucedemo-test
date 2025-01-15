package saucedemo_ui.util;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;

public class BrowserUtils {
    // Method to dismiss password popup
    public static void dismissPasswordPopup() {


        try {
            // Try to switch to the alert
            Alert alert = Selenide.switchTo().alert();
            // If the alert is present, accept it (click OK)
            alert.accept();
        } catch (NoAlertPresentException e) {
            // No alert is present, just log or do nothing
            System.out.println("No alert present.");
        }
    }
}
