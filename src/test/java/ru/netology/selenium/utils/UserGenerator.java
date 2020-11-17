package ru.netology.selenium.utils;

import ru.netology.selenium.domain.Card;
import ru.netology.selenium.domain.User;

import java.util.ArrayList;
import java.util.List;

import static ru.netology.selenium.constants.Constants.*;

public class UserGenerator {

    private UserGenerator() {}

    public static User getUser() {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(FIRST_CARD_NUMBER, FIRST_CARD_PATTERN));
        cards.add(new Card(SECOND_CARD_NUMBER, SECOND_CARD_PATTERN));
        return new User(LOGIN, PASSWORD, VERIFICATION_CODE, cards);
    }
}
