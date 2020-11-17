package ru.netology.selenium.domain;

public class Card {
    String number;
    String pattern;

    public Card(String number, String pattern) {
        this.number = number;
        this.pattern = pattern;
    }

    public String getNumber() {
        return number;
    }

    public String getPattern() {
        return pattern;
    }
}
