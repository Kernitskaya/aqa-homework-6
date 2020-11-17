package ru.netology.selenium.utils;

import ru.netology.selenium.domain.User;
import ru.netology.selenium.pages.AuthPage;
import ru.netology.selenium.pages.CardsPage;

import static ru.netology.selenium.constants.Constants.*;

public class TearDownHelper {

    public static void setCardsBalanceToDefaultState(User user) {
        int firstCardBalance = AuthPage.newInstance().logIn(user)
                .fillCodeAndConfirm(user.getVerificationCode()).getCardBalanceByCardPattern(FIRST_CARD_PATTERN);
        int secondCardBalance = CardsPage.newInstance().getCardBalanceByCardPattern(SECOND_CARD_PATTERN);
        int diff = firstCardBalance > DEFAULT_CARD_BALANCE ? firstCardBalance - DEFAULT_CARD_BALANCE
                                                            : secondCardBalance - DEFAULT_CARD_BALANCE;
        if (firstCardBalance > DEFAULT_CARD_BALANCE) {
            setCardBalance(SECOND_CARD_PATTERN, FIRST_CARD_NUMBER, String.valueOf(diff));
        }
        if (secondCardBalance > DEFAULT_CARD_BALANCE) {
            setCardBalance(FIRST_CARD_PATTERN, SECOND_CARD_NUMBER, String.valueOf(diff));
        }
    }

    private static void setCardBalance(String fromCard, String toCard, String payment) {
        CardsPage.newInstance().openCardByCardPattern(fromCard).fillAndTransact(payment, toCard);
    }
}
