package main.java.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public abstract class InnerPage extends Page {
    @FindBy(how = How.XPATH, xpath = "//ul[@class=\"nav nav-tabs\"]/li")
    protected SelenideElement navigationTabsElement;
}
