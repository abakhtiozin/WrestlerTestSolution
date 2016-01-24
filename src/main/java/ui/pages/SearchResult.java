package main.java.ui.pages;

import com.codeborne.selenide.SelenideElement;
import main.java.model.Wrestler;
import main.java.ui.Filter;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$;

public class SearchResult extends InnerPage {

    private List<SearchResultRow> resultRows() {
        List<SelenideElement> rows = $$(".row [ng-click=\"openWrestler(wrestler)\"]");
        return rows.stream()
                .map(SearchResultRow::new)
                .collect(Collectors.toList());
    }

    @Step
    public boolean areResultsEqualToFilter(Filter filter) {
        for (SearchResultRow searchResultRow : resultRows()) {

            if (filter.getRegion() != null) {
                if (!searchResultRow.getRegion().equals(filter.getRegion())) {
                    return false;
                }
            }

            if (filter.getClub() != null) {
                if (!searchResultRow.getFootBallClub().equals(filter.getClub())) {
                    return false;
                }
            }

            if (filter.getExpireYear() != null) {
                if (!searchResultRow.getLicense().equals(filter.getExpireYear())) {
                    return false;
                }
            }

            if (filter.getStyle() != null) {
                if (!searchResultRow.getStyle().equals(filter.getStyle())) {
                    return false;
                }
            }

        }
        return true;
    }

    @Step
    public boolean areResultsEqualToSearchByLastName(String name) {
        for (SearchResultRow searchResultRow : resultRows()) {
            if (!searchResultRow.getFullName().contains(name)) {
                return false;
            }
        }
        return true;
    }

    @Step
    public boolean isSearchResultEmpty() {
        return resultRows().size() == 0;
    }

    @Step
    public WrestlerPage chooseWrestler(Wrestler wrestler) {
        for (SearchResultRow searchResultRow : resultRows()) {
            if (searchResultRow.compareTo(wrestler)) {
                return searchResultRow.goToWrestlerPage();
            }
        }
        throw new NullPointerException("Wrestler was not found");
    }

     //method for clean up
    public WrestlerPage chooseFirstWrestler() {
        for (SearchResultRow searchResultRow : resultRows()) {
            return searchResultRow.goToWrestlerPage();
        }
        throw new NullPointerException("Wrestler was not found");
    }

     //method for clean up
    public int totalSearchResults() {
        return resultRows().size();
    }
}
