package main.java.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import main.java.model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class WrestlerPage extends InnerPage {

    @FindBy(how = How.CSS, css = "input[placeholder=\"Last name\"]")
    private SelenideElement lastNameField;

    @FindBy(how = How.CSS, css = "input[placeholder=\"First name\"]")
    private SelenideElement firstNameField;

    @FindBy(how = How.CSS, css = "input[placeholder=\"Middle name\"]")
    private SelenideElement middleNameField;

    @FindBy(how = How.CSS, css = "input[placeholder=\"Date of Birth\"]")
    private SelenideElement birthDateField;

    @FindBy(how = How.CSS, css = "[value=\"wr.region1\"] select")
    private SelenideElement region1Field;

    @FindBy(how = How.CSS, css = "[value=\"wr.region2\"] select")
    private SelenideElement region2Field;

    @FindBy(how = How.CSS, css = "[value=\"wr.fst1\"] select")
    private SelenideElement footballClub1Field;

    @FindBy(how = How.CSS, css = "[value=\"wr.fst2\"] select")
    private SelenideElement footballClub2Field;

    @FindBy(how = How.CSS, css = "[value=\"wr.trainer1\"] input")
    private SelenideElement trainer1Field;

    @FindBy(how = How.CSS, css = "[value=\"wr.trainer2\"] input")
    private SelenideElement trainer2Field;

    @FindBy(how = How.CSS, css = "[value=\"wr.style\"] select")
    private SelenideElement styleField;

    @FindBy(how = How.CSS, css = "[value=\"wr.lictype\"] select")
    private SelenideElement ageField;

    @FindBy(how = How.CSS, css = "[value=\"wr.expires\"] select")
    private SelenideElement expireField;

    @FindBy(how = How.CSS, css = "f-select[value=\"wr.card_state\"] select")
    private SelenideElement cardStateField;

    @FindBy(how = How.CSS, css = "button.btn-success")
    private SelenideElement successButton;

    @FindBy(how = How.CSS, css = "button[ng-click=\"delete()\"]")
    private SelenideElement deleteButton;

    @Step
    public DashBoardPage deleteWrestler() {
        deleteButton.click();
        $(".modal-dialog").waitUntil(Condition.appear, 10000);
        confirmDelete();
        return page(DashBoardPage.class);
    }

    @Step
    public WrestlerPage uploadImage() {
        File image = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\hqdefault.jpg");
        $("input[uploader=\"photoUploader\"]").uploadFile(image);
        return this;
    }

    @Step
    public WrestlerPage uploadAttachments(final String attachmentName) {
        File attachment = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\" + attachmentName);
        $("input[uploader=\"attachUploader\"]").uploadFile(attachment);
        return this;
    }

    @Step
    public boolean verifyThatImageUploaded() {
        return $(".panel-body .photo-drop img").exists();
    }

    @Step
    public boolean verifyThatAttachmentUploaded(String attachmentName) {
        return $(".file-drop td a").getText().contains(attachmentName);
    }

    @Step
    public boolean verifyThatThereIsNoAttachment(String attachmentName) {
        return $(".file-drop").getText().contains(attachmentName);
    }


    @Step
    public WrestlerPage deleteAttachment(String attachmentName) {
        $(By.xpath("//tr[@ng-repeat='attach in wr.attaches' and contains(.,'" + attachmentName + "')]/td/ico[@ng-click='deleteAttach($index)']")).click();
        return page(WrestlerPage.class);
    }


    @Step
    private void confirmDelete() {
        $(By.xpath("//button[@class='btn btn-success' and contains(.,'Так')]")).click();
    }

    @Step
    public WrestlerPage addNewWrestler(Wrestler wrestler) {

        lastNameField.setValue(wrestler.getLastName());
        firstNameField.setValue(wrestler.getFirstName());
        middleNameField.setValue(wrestler.getMiddleName());
        birthDateField.setValue(wrestler.getDateOfBirth());

        region1Field.selectOption(wrestler.getFootballClub1().getRegion().getName());
        footballClub1Field.selectOption(wrestler.getFootballClub1().getClub().getName());
//          because of bug in web_app that doesn't save this fields when you create Wrestler by first time
//        trainer1Field.setValue(wrestler.getFootballClub1().getTrainer());
//
//        region2Field.selectOption(wrestler.getFootballClub2().getRegion().getName());
//        footballClub2Field.selectOption(wrestler.getFootballClub2().getClub().getName());
//        trainer2Field.setValue(wrestler.getFootballClub2().getTrainer());

        styleField.selectOption(wrestler.getStyle().name());
        ageField.selectOption(wrestler.getAge().getName());
        expireField.selectOption(wrestler.getExpireYear());
        cardStateField.selectOption(wrestler.getCardStatus().getName());

        successButton.click();
        SelenideElement wrestlerTab = navigationTabsElement.$(By.xpath("//a[contains(.,'" + wrestler.getLastName() + "')]"));
        wrestlerTab.waitUntil(Condition.appear, 5000);
        closeTab(wrestler.getLastName());
        return this;
    }

    @Step
    public DashBoardPage closeTab(String lastName) {
        navigationTabsElement.$(By.xpath("//a[contains(.,'" + lastName + "')]//div[@class=\"close-it\"]/ico")).click();
        return page(DashBoardPage.class);
    }

    @Step
    public Wrestler getWrestler() {
        Wrestler wrestler = new Wrestler();

        wrestler.setLastName(lastNameField.getText());
        wrestler.setFirstName(firstNameField.getText());
        wrestler.setMiddleName(middleNameField.getText());
        wrestler.setDateOfBirth(birthDateField.getText());

        FootballClub footballClub1 = new FootballClub();
        footballClub1.setClub(Club.valueOf(footballClub1Field.getSelectedText()));
        footballClub1.setRegion(Region.valueOf(region1Field.getSelectedText()));
//because of bug in web_app that doesn't save this fields when you create Wrestler by first time
//        footballClub1.setTrainer(trainer1Field.getText());
        wrestler.setFootballClub1(footballClub1);

//because of bug in web_app that doesn't save this fields when you create Wrestler by first time
//        FootballClub footballClub2 = new FootballClub();
//        footballClub2.setClub(Club.valueOf(footballClub2Field.getSelectedText()));
//        footballClub2.setRegion(Region.valueOf(region2Field.getSelectedText()));
//        footballClub2.setTrainer(trainer2Field.getText());
//        wrestler.setFootballClub2(footballClub2);

        wrestler.setStyle(Style.valueOf(styleField.getSelectedText()));
        wrestler.setAge(Age.valueOf(ageField.getSelectedText()));
        wrestler.setExpireYear(expireField.getSelectedText());
        wrestler.setCardStatus(CardStatus.valueOf(cardStateField.getSelectedText()));
        return wrestler;
    }

    @Step
    public WrestlerPage update(Wrestler wrestler) {
        //Should say sorry for this mess, didn't have enough time to make it better
        // here i'm trying to update field's on wrestler card so I need to know is wrestler.value not null
        // and wrestler.value doesn't equal value in wrestler card in particular field
        if (wrestler.getLastName() != null && !wrestler.getLastName().equals(lastNameField.val()))
            lastNameField.setValue(wrestler.getLastName());

        if (wrestler.getFirstName() != null && !wrestler.getFirstName().equals(firstNameField.val()))
            firstNameField.setValue(wrestler.getFirstName());

        if (wrestler.getMiddleName() != null && !wrestler.getMiddleName().equals(middleNameField.val()))
            middleNameField.setValue(wrestler.getMiddleName());

        if (wrestler.getDateOfBirth() != null && !wrestler.getDateOfBirth().equals(birthDateField.val()))
            birthDateField.setValue(wrestler.getDateOfBirth());

        if (wrestler.getFootballClub1() != null) {
            if (wrestler.getFootballClub1().getRegion() != null &&
                    !wrestler.getFootballClub1().getRegion().equals(Region.valueOf(region1Field.getSelectedText())))
                region1Field.selectOption(wrestler.getFootballClub1().getRegion().getName());

            if (wrestler.getFootballClub1().getClub() != null
                    && !wrestler.getFootballClub1().getClub().equals(Club.valueOf(footballClub1Field.getSelectedText().toUpperCase())))
                footballClub1Field.selectOption(wrestler.getFootballClub1().getClub().getName());

            if (wrestler.getFootballClub1().getTrainer() != null
                    && !wrestler.getFootballClub1().getTrainer().equals(trainer1Field.val()))
                trainer1Field.setValue(wrestler.getFootballClub1().getTrainer());
        }


        if (wrestler.getFootballClub2() != null) {
            if (wrestler.getFootballClub2().getRegion() != null
                    && !wrestler.getFootballClub2().getRegion().equals(Region.valueOf(region2Field.getSelectedText())))
                region2Field.selectOption(wrestler.getFootballClub2().getRegion().getName());

            if (wrestler.getFootballClub2().getClub() != null
                    && !wrestler.getFootballClub2().getClub().equals(Club.valueOf(footballClub2Field.getSelectedText().toUpperCase())))
                footballClub2Field.selectOption(wrestler.getFootballClub2().getClub().getName());

            if (wrestler.getFootballClub2().getTrainer() != null
                    && !wrestler.getFootballClub2().getTrainer().equals(trainer2Field.val()))
                trainer2Field.setValue(wrestler.getFootballClub2().getTrainer());
        }


        if (wrestler.getStyle() != null
                && !wrestler.getStyle().equals(Style.valueOf(styleField.getSelectedText())))
            styleField.selectOption(wrestler.getStyle().name());

        if (wrestler.getAge() != null
                && !wrestler.getAge().getName().equals(ageField.getSelectedText()))
            ageField.selectOption(wrestler.getAge().getName());

        if (wrestler.getExpireYear() != null
                && !wrestler.getExpireYear().equals(expireField.getSelectedText()))
            expireField.selectOption(wrestler.getExpireYear());

        if (wrestler.getCardStatus() != null
                && !wrestler.getCardStatus().getName().equals(cardStateField.getSelectedText().toUpperCase()))
            cardStateField.selectOption(wrestler.getCardStatus().getName());

        successButton.click();
        navigationTabsElement.$(By.xpath("//a[contains(.,'" + wrestler.getLastName() + "')]")).waitUntil(Condition.appear, 5000);
        return this;
    }

}
