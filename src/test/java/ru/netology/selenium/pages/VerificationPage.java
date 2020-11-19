package ru.netology.selenium.pages;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.tr.Ve;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    SelenideElement codeInput = $("[type='text']");
    SelenideElement confirm = $("[type='button']");

    private VerificationPage() {}

    public CardsPage fillCodeAndConfirm(String code) {
        codeInput.setValue(code);
        confirm.click();
        return CardsPage.newInstance();
    }

    public static VerificationPage newInstance() {
        return new VerificationPage();
    }
}
