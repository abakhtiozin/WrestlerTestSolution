package main.java.ui.pages;

import com.codeborne.selenide.Selenide;

public class Pages {
    private static <T extends Page> T getPage(Class<T> page) {
        return Selenide.page(page);
    }

    public static DashBoardPage dashBoardPage() {
        return getPage(DashBoardPage.class);
    }

    public static LoginPage loginPage() {
        return getPage(LoginPage.class);
    }

    public static WrestlerPage wrestlerPage() {
        return getPage(WrestlerPage.class);
    }

    public static SearchResult searchResult() {
        return getPage(SearchResult.class);
    }

}
