package ru.netology.selenium.domain;

import java.util.List;

public class User {
    String name;
    String password;
    String verificationCode;
    List<Card> cards;

    public User(String name, String password, String verificationCode, List<Card> cards) {
        this.name = name;
        this.password = password;
        this.verificationCode = verificationCode;
        this.cards = cards;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public Card getSecondCard() {
        return cards.get(1);
    }
}
