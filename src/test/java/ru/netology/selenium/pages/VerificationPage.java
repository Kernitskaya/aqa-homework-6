package ru.netology.selenium.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    SelenideElement codeInput = $("[type='text']");
    SelenideElement confirm = $("[type='button']");

    public CardsPage fillCodeAndConfirm(String code) {
        codeInput.setValue(code);
        confirm.click();
        return CardsPage.newInstance();
    }
}
