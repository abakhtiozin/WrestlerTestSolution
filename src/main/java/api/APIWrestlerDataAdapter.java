package main.java.api;

import main.java.model.*;

public class APIWrestlerDataAdapter {
    public static String adapt(Style style) {
        return style.getName();
    }

    public static String adapt(Club club) {
        return club.getId();
    }

    public static String adapt(Region region) {
        return region.getId();
    }

    public static String adapt(Age age) {
        return age.getId();
    }

    public static String adapt(CardStatus cardStatus) {
        return cardStatus.getId();
    }
}
