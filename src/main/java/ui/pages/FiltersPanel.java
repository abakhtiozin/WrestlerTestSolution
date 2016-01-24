package main.java.ui.pages;

import main.java.model.Club;
import main.java.model.Region;
import main.java.model.Style;
import main.java.ui.Photo;

import static com.codeborne.selenide.Selenide.$;

public class FiltersPanel {
    protected void resetFilters() {
        $("button[ng-click=\"resetFilters()\"]").click();
    }

    protected void setFilterByRegion(Region region) {
        $("select[ng-model=\"filters.fregion\"]").selectOption(region.getName());
    }

    protected void setFilterByClub(Club club) {
        $("select[ng-model=\"filters.ffst\"]").selectOption(club.getName());
    }

    protected void setFilterByYear(String expireYear) {
        $("select[ng-model=\"filters.fyear\"]").selectOption(expireYear);
    }

    protected void setFilterByStyle(Style style) {
        $("select[ng-model=\"filters.fstyle\"").selectOption(style.name());
    }

    protected void setFilterByPhotoExistence(Photo photo) {
        $("select[ng-model=\"filters.fphoto\"").selectOption(photo.name());
    }

}
