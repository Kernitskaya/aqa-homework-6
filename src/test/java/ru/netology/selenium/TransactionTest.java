package ru.netology.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.netology.selenium.domain.User;
import ru.netology.selenium.pages.AuthPage;
import ru.netology.selenium.pages.CardsPage;
import ru.netology.selenium.utils.TearDownHelper;
import ru.netology.selenium.utils.UserGenerator;

import static com.codeborne.selenide.Selenide.open;


public class TransactionTest {
    private String startUrl = "http://localhost:9999";

    @AfterEach
    void setDefaultState() {
        open(startUrl);
        User user = UserGenerator.getUser();
        TearDownHelper.setCardsBalanceToDefaultState(user);
    }

    @Test
    void testSuccessTransaction() {
        String firstCardExpectedResult = "10500";
        String secondCardExpectedResult = "9500";
        open(startUrl);
        User user = UserGenerator.getUser();
        AuthPage.newInstance().logIn(user)
                .fillCodeAndConfirm(user.getVerificationCode()).openCardByCardPattern(user.getFirstCard().getPattern())
                .fillAndTransact("500", user.getSecondCard().getNumber());
        CardsPage.newInstance().checkCardBalanceByPattern(user.getFirstCard().getPattern(), firstCardExpectedResult);
        CardsPage.newInstance().checkCardBalanceByPattern(user.getSecondCard().getPattern(), secondCardExpectedResult);
    }

    @Test
    void testShowUnderLimitErrorNotification() {
        open(startUrl);
        User user = UserGenerator.getUser();
        AuthPage.newInstance().logIn(user)
                .fillCodeAndConfirm(user.getVerificationCode()).openCardByCardPattern(user.getSecondCard().getPattern())
                .fillAndTransact("10500", user.getFirstCard().getNumber());
        CardsPage.newInstance().checkUnderLimitNotificationVisible();
    }

    @Test
    void testShowSameCardErrorNotification() {
        open(startUrl);
        User user = UserGenerator.getUser();
        AuthPage.newInstance().logIn(user)
                .fillCodeAndConfirm(user.getVerificationCode()).openCardByCardPattern(user.getFirstCard().getPattern())
                .fillAndTransact("500", user.getFirstCard().getNumber());
        CardsPage.newInstance().checkSameCardNotificationVisible();
    }
}
