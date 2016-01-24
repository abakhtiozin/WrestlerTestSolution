package main.java.ui.pages;

import com.codeborne.selenide.SelenideElement;
import main.java.model.Club;
import main.java.model.Region;
import main.java.model.Style;
import main.java.model.Wrestler;
import main.java.ui.Photo;

import static com.codeborne.selenide.Selenide.page;

public class SearchResultRow {
    private SelenideElement row;

    public SearchResultRow(SelenideElement row) {
        this.row = row;
    }

    protected String getId() {
        return row.$(" td:nth-of-type(1)").getText();
    }

    protected String getFullName() {
        return row.$(" td:nth-of-type(2)").getText();
    }

    protected Region getRegion() {
        String region = row.$(" td:nth-of-type(3)").getText();
        return Region.valueOf(region);
    }

    protected Club getFootBallClub() {
        String footballClub = row.$(" td:nth-of-type(4)").getText();
        return Club.valueOf(footballClub.toUpperCase());
    }

    protected String getLicense() {
        return row.$(" td:nth-of-type(5)").getText();
    }

    protected Photo getPhoto() {
        return Photo.valueOf(row.$(" td:nth-of-type(6)").getText().toUpperCase());
    }

    protected Style getStyle() {
        return Style.valueOf(row.$(" td:nth-of-type(7)").getText().toUpperCase());
    }

    protected boolean compareTo(Wrestler wrestler) {
        String fullName = wrestler.getLastName() + " " + wrestler.getFirstName() + " " + wrestler.getMiddleName();
        if (!getFullName().equals(fullName)) return false;
        if (!getRegion().equals(wrestler.getFootballClub1().getRegion())) return false;
        if (!getFootBallClub().equals(wrestler.getFootballClub1().getClub())) return false;
        if (!getLicense().equals(wrestler.getExpireYear())) return false;
        if (!getStyle().equals(wrestler.getStyle())) return false;
        return true;
    }

    protected WrestlerPage goToWrestlerPage() {
        row.click();
        return page(WrestlerPage.class);
    }
}
