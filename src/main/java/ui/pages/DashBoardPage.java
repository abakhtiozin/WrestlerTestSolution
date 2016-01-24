package main.java.ui.pages;

import com.codeborne.selenide.SelenideElement;
import main.java.ui.Filter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.page;

public class DashBoardPage extends InnerPage {
    private FiltersPanel filtersPanel = new FiltersPanel();

    @FindBy(how = How.CSS, css = "div[ng-controller=\"WrestlerListCtrl\"]")
    private SelenideElement wrestlerListCtrlElement;

    @FindBy(how = How.CSS, css = "button[ng-click=\"newWrestler()\"]")
    private SelenideElement newButtonElement;

    @FindBy(how = How.CSS, css = "input[ng-model=\"searchFor\"]")
    private SelenideElement searchField;

    @FindBy(how = How.CSS, css = ".btn.btn-primary[type=\"submit\"]")
    private SelenideElement searchButton;

    @Step
    public WrestlerPage addWrestler() {
        newButtonElement.click();
        return page(WrestlerPage.class);
    }

    @Step
    public void searchByLastName(String lastName) {
        searchField.setValue(lastName);
        searchButton.click();
    }

    @Step
    public void searchByFilters(Filter filter) {
        setUpFilters(filter);
    }

    @Step
    public void resetFilterByFilters() {
        filtersPanel.resetFilters();
    }

    private void setUpFilters(Filter filter) {
        if (filter.getClub() != null) filtersPanel.setFilterByClub(filter.getClub());
        if (filter.getRegion() != null) filtersPanel.setFilterByRegion(filter.getRegion());
        if (filter.getExpireYear() != null) filtersPanel.setFilterByYear(filter.getExpireYear());
        if (filter.getStyle() != null) filtersPanel.setFilterByStyle(filter.getStyle());
        if (filter.getPhoto() != null) filtersPanel.setFilterByPhotoExistence(filter.getPhoto());
    }

    @Step
    public DashBoardPage goToDashBoard() {
        navigationTabsElement.$(By.xpath("./a[contains(.,'Wrestlers')]")).click();
        return page(DashBoardPage.class);
    }
}
