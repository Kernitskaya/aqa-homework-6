package ru.netology.selenium.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class ReplenishmentPage {

    SelenideElement amountField = $("[type='text']");
    SelenideElement fromField = $("[type='tel']");
    SelenideElement confirmButton = $("[type='button']");

    private void fillPayment(String payment) {
        amountField.sendKeys(Keys.BACK_SPACE);
        amountField.setValue(payment);
    }

    private void fillFrom(String fromAccount) {
        fromField.sendKeys(Keys.BACK_SPACE);
        fromField.setValue(fromAccount);
    }

    public void fillAndTransact(String payment, String fromAccount) {
        fillPayment(payment);
        fillFrom(fromAccount);
        confirmButton.click();
    }
}
