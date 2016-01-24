package main.java.util;

import main.java.ui.Filter;

public class FilterCreator {
    public static Filter lastFilter;
    public static Filter createFilter() {
        lastFilter = new Filter();
        return lastFilter;
    }

}
