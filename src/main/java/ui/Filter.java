package main.java.ui;

import main.java.model.Club;
import main.java.model.Region;
import main.java.model.Style;

public class Filter {
    private Region region;
    private Club club;
    private String expireYear;
    private Style style;
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public Filter withPhoto(Photo photo) {
        this.photo = photo;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Filter withStyle(Style style) {
        this.style = style;
        return this;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public Filter withExpireYear(String expireYear) {
        this.expireYear = expireYear;
        return this;
    }

    public Club getClub() {
        return club;
    }

    public Filter withClub(Club club) {
        this.club = club;
        return this;
    }

    public Region getRegion() {
        return region;
    }

    public Filter withRegion(Region region) {
        this.region = region;
        return this;
    }


}
