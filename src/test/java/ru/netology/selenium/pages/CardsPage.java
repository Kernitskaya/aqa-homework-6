package ru.netology.selenium.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
    private final String pattern = "%s, баланс: %s р.\nПополнить";

    private ElementsCollection cards = $$(".list__item");

    private CardsPage() {}

    public static CardsPage newInstance() {
        return new CardsPage();
    }

    public int getCardBalanceByCardPattern(String cardPattern) {
        return extractBalance(cards.find(text(cardPattern)).text());
    }

    private int extractBalance(String cardText) {
        return Integer.parseInt(
                cardText.split("баланс: ")[1].trim().split("р")[0].trim());
    }

    public ReplenishmentPage openCardByCardPattern(String cardPattern) {
        cards.find(text(cardPattern)).$("button").click();
        return new ReplenishmentPage();
    }

    public void checkCardBalanceByPattern(String cardPattern, String expectedBalance) {
        cards.find(text(cardPattern)).shouldHave(text(String.format(pattern, cardPattern, expectedBalance)));
    }
}
