package Pages;

import Common.Methods;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class LoginPage extends Methods {

    private SelenideElement loginField = $(By.id("cvcvc"));
    private SelenideElement passField = $(By.id("sdf"));
    private SelenideElement logBtn = $(By.id("dfd"));

    public NextPage loginProcess(String login, String pwd) {

        clearBrowserCache();
        open("http://www.wp.pl");
        loginField.setValue(login);
        passField.setValue(pwd);
        logBtn.click();
        return new NextPage();
    }

}
