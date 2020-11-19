package ru.netology.selenium.pages;

import cucumber.api.java.ru.Пусть;

import com.codeborne.selenide.SelenideElement;
import ru.netology.selenium.domain.User;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    private SelenideElement login = $("[name='login']");
    private SelenideElement password = $("[name='password']");
    private SelenideElement confirm = $("[type='button']");

    private AuthPage() {}

    public static AuthPage newInstance() {
        return new AuthPage();
    }

    public VerificationPage logIn(User user) {
        this.login.setValue(user.getName());
        this.password.setValue(user.getPassword());
        confirm.click();
        return VerificationPage.newInstance();
    }

    public VerificationPage logIn(String login, String password) {
        this.login.setValue(login);
        this.password.setValue(password);
        confirm.click();
        return VerificationPage.newInstance();
    }
}
