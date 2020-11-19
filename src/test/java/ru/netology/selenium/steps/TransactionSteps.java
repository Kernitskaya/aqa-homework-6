package ru.netology.selenium.steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.И;
import static com.codeborne.selenide.Selenide.open;

import cucumber.api.java.ru.Тогда;
import ru.netology.selenium.domain.User;
import ru.netology.selenium.pages.AuthPage;
import ru.netology.selenium.pages.CardsPage;
import ru.netology.selenium.pages.VerificationPage;
import ru.netology.selenium.utils.TearDownHelper;
import ru.netology.selenium.utils.UserGenerator;

public class TransactionSteps {
    private static String startUrl = "http://localhost:9999";
    private static User user = UserGenerator.getUser();

    @И("^авторизоваться с логином \"([^\"]*)\" и паролем \"([^\"]*)\"$")
    public static void logIn(String login, String password) {
        AuthPage.newInstance().logIn(login, password);
    }

    @Если("^запустить сервис перевода средств между своими картами")
    public static void openService() {
        open(startUrl);
    }

    @И("^ввести код верификации \"([^\"]*)\"$")
    public static void enterVerificationCode(String code) {
        VerificationPage.newInstance().fillCodeAndConfirm(code);
    }

    @И("^перевести \"([^\"]*)\" рублей на (первую|вторую) карту, с карты с номером \"([^\"]*)\"$")
    public static void fillAndTransact(String payment, String toCard, String cardNumber) {
        CardsPage.newInstance().openCardByCardPattern(toCard.equals("первую")
                ? user.getFirstCard().getPattern()
                : user.getSecondCard().getPattern()).fillAndTransact(payment, cardNumber);
    }

    @Тогда("^баланс на (первой|второй) карте из списка на главной странице должен стать \"([^\"]*)\" рублей$")
    public static void checkCardBalance(String card, String expectedAmount) {
        CardsPage.newInstance().checkCardBalanceByPattern(card.equals("первой")
                        ? user.getFirstCard().getPattern()
                        : user.getSecondCard().getPattern(),
                expectedAmount);
    }

    @Дано("на каждой из карт по 10000 рублей$")
    public static void setCardToDefaultState() {
        open(startUrl);
        TearDownHelper.setCardsBalanceToDefaultState(user);
    }
}
