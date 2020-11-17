package ru.netology.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
    private final String pattern = "%s, баланс: %s р.\nПополнить";

    private ElementsCollection cards = $$(".list__item");
    private ElementsCollection topUpButtons = $$(byText("Пополнить"));
    private SelenideElement refreshButton = $(byText("Обновить"));

    private CardsPage() {}

    public static CardsPage newInstance() {
        return new CardsPage();
    }


    public int getCardBalanceByCardPattern(String cardPattern) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i=0; i < cards.size(); i++) {
            String cardText = cards.get(i).text();
            if (cardText.contains(cardPattern)) {
                return extractBalance(cardText);
            }
        }
        return 0;
    }

    private int extractBalance(String cardText) {
        return Integer.parseInt(
                cardText.split("баланс: ")[1].trim().split("р")[0].trim());
    }

    public ReplenishmentPage openCardByCardPattern(String cardPattern) {
        for (int i=0; i < cards.size(); i++) {
            String rowText = cards.get(i).text();
            if (rowText.contains(cardPattern)) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                topUpButtons.get(i).click();
                break;
            }
        }
        return new ReplenishmentPage();
    }

    public void checkCardBalanceByPattern(String cardPattern, String expectedBalance) {
        for (SelenideElement card: cards) {
            if (card.text().contains(cardPattern)) {
                card.shouldHave(text(String.format(pattern, cardPattern, expectedBalance)));
                return;
            }
        }
    }
}
