package test.java.ui;

import main.java.ui.pages.Pages;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import static main.java.util.FilterCreator.createFilter;
import static main.java.util.FilterCreator.lastFilter;
import static main.java.util.WrestlerDataGenerator.generateWrestler;
import static main.java.util.WrestlerDataGenerator.lastGeneratedWrestler;


public class BrowserScenariosTest {

    @Test
    @Features("Wrestler Creation")
    @Stories({"Create", "Search", "Update", "Delete"})
    public void Scenario1() {
        Pages.loginPage().goToLoginPage();
        Pages.loginPage().login();

        Pages.dashBoardPage().addWrestler();

        Pages.wrestlerPage().addNewWrestler(generateWrestler());

        Pages.dashBoardPage().goToDashBoard();
        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());

        Pages.searchResult().chooseWrestler(lastGeneratedWrestler);
        lastGeneratedWrestler.setMiddleName("Updated");

        Pages.wrestlerPage().update(lastGeneratedWrestler);
        Pages.wrestlerPage().deleteWrestler();

        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());

        Assert.assertTrue(Pages.searchResult().isSearchResultEmpty());
    }

    @Test
    @Features("Filter's work")
    @Stories({"Select at least 2 filters and verify correct record in table"})
    public void Scenario2() {
        Pages.loginPage().goToLoginPage();
        Pages.loginPage().login();

        Pages.dashBoardPage().addWrestler();
        Pages.wrestlerPage().addNewWrestler(generateWrestler());
        Pages.dashBoardPage().goToDashBoard();

        Pages.dashBoardPage().searchByFilters(createFilter()
                .withRegion(lastGeneratedWrestler.getFootballClub1().getRegion())
                .withClub(lastGeneratedWrestler.getFootballClub1().getClub())
        );

        Assert.assertTrue(Pages.searchResult().areResultsEqualToFilter(lastFilter));
    }

    @Test
    @Features("Upload photo")
    @Stories({"Create", "Search", "Upload photo"})
    public void Scenario3() {
        Pages.loginPage().goToLoginPage();
        Pages.loginPage().login();

        Pages.dashBoardPage().addWrestler();
        Pages.wrestlerPage().addNewWrestler(generateWrestler());
        Pages.dashBoardPage().goToDashBoard();

        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());
        Pages.searchResult().chooseWrestler(lastGeneratedWrestler);
        Pages.wrestlerPage().uploadImage();
        lastGeneratedWrestler.setMiddleName("Updated");
        Pages.wrestlerPage().update(lastGeneratedWrestler);
        Pages.wrestlerPage().closeTab(lastGeneratedWrestler.getLastName());

        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());
        Pages.searchResult().chooseWrestler(lastGeneratedWrestler);

        Assert.assertTrue(Pages.wrestlerPage().verifyThatImageUploaded());
    }

    @Test
    @Features("Upload attachments")
    @Stories({"Create", "Search", "Upload attachment"})
    public void Scenario4() {
        Pages.loginPage().goToLoginPage();
        Pages.loginPage().login();

        Pages.dashBoardPage().addWrestler();
        Pages.wrestlerPage().addNewWrestler(generateWrestler());
        Pages.dashBoardPage().goToDashBoard();

        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());
        Pages.searchResult().chooseWrestler(lastGeneratedWrestler);
        Pages.wrestlerPage().uploadAttachments("Test Cases.txt");
        lastGeneratedWrestler.setMiddleName("Updated");
        Pages.wrestlerPage().update(lastGeneratedWrestler);
        Pages.wrestlerPage().closeTab(lastGeneratedWrestler.getLastName());

        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());
        Pages.searchResult().chooseWrestler(lastGeneratedWrestler);

        Assert.assertTrue(Pages.wrestlerPage().verifyThatAttachmentUploaded("Test Cases.txt"));

        Pages.wrestlerPage().deleteAttachment("Test Cases.txt");
        Pages.wrestlerPage().closeTab(lastGeneratedWrestler.getLastName());

        Pages.dashBoardPage().searchByLastName(lastGeneratedWrestler.getLastName());
        Pages.searchResult().chooseWrestler(lastGeneratedWrestler);
        Assert.assertFalse(Pages.wrestlerPage().verifyThatThereIsNoAttachment("Test Cases.txt"));
    }

    // run this method in case of clean up application from all data that tests made
    public void cleanUp() {
        Pages.loginPage().goToLoginPage();
        Pages.loginPage().login();
        Pages.dashBoardPage().searchByLastName("Bakhtiozin");
        for (int i = 0; i < Pages.searchResult().totalSearchResults(); i++) {
            Pages.dashBoardPage().searchByLastName("Bakhtiozin");
            Pages.searchResult().chooseFirstWrestler();
            Pages.wrestlerPage().deleteWrestler();
        }
    }
}
