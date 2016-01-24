package main.java.ui.pages;

import com.codeborne.selenide.SelenideElement;
import main.java.ui.User;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage extends Page {
    @FindBy(how = How.CSS, css = ".login-form")
    private SelenideElement loginForm;

    @Step
    public DashBoardPage login() {
        User user = new User("auto", "test"); //user is always the same

        loginForm.$(" #username input").setValue(user.getName());
        loginForm.$("input[type=\"password\"]").setValue(user.getPassword());
        loginForm.$("button[type=\"submit\"]").click();
        return page(DashBoardPage.class);
    }

    @Step
    public LoginPage goToLoginPage() {
        return open("http://streamtv.net.ua/base", LoginPage.class);
    }
}
