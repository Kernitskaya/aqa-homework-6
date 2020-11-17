package ru.netology.selenium.pages;

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
        return new VerificationPage();
    }
}
