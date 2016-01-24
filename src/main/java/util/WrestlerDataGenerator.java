package main.java.util;

import main.java.model.*;

import java.util.Random;

public class WrestlerDataGenerator {
    private static Random random = new Random();
    public static Wrestler lastGeneratedWrestler;

    public static Wrestler generateWrestler() {
        Wrestler wrestler = new Wrestler();

        wrestler.setLastName("Bakhtiozin" + getRandomString(2));
        wrestler.setFirstName(getRandomLiteralsString());
        wrestler.setMiddleName(getRandomLiteralsString());
        wrestler.setDateOfBirth(generateRandomBirthDate());

        wrestler.setFootballClub1(generateFootBallClub());
//because of bug in web_app that doesn't save this fields when you create Wrestler by first time
//        wrestler.setFootballClub2(generateFootBallClub()); because of bug in web_app

        wrestler.setAge(generateRandomAge());
        wrestler.setCardStatus(generateRandomCardStatus());
        wrestler.setStyle(generateRandomStyle());
        wrestler.setExpireYear(generateRandomExpireYear());

        lastGeneratedWrestler = wrestler;
        return wrestler;

    }

    private static String getRandomString(int top) {
        String dat = "";
        for (int i = 0; i < top; i++) {
            dat = (char) (int) ((random.nextInt(25) + 97)) + dat;
        }
        return dat;
    }

    private static String getRandomLiteralsString() {
        return getRandomString(randomNumber(4, 9));
    }

    private static Integer randomNumber(int min, int max) {
        if (max - min == 0)
            return min;
        else return random.nextInt(max - min) + min;
    }

    private static String generateRandomBirthDate() {
        String year = String.valueOf(randomNumber(1940, 1990));
        int rndMonth = randomNumber(1, 12);
        String month = String.valueOf(rndMonth < 10 ? "0" + rndMonth : rndMonth);
        int rndDay = randomNumber(1, 29);
        String day = String.valueOf(rndDay < 10 ? "0" + rndDay : rndDay);
        return day + "-" + month + "-" + year;
    }

    private static FootballClub generateFootBallClub() {
        FootballClub footballClub = new FootballClub();
//because of bug in web_app that doesn't save this fields when you create Wrestler by first time
//        footballClub.setTrainer(getRandomLiteralsString()); because of bug in web_app
        footballClub.setClub(generateRandomClub());
        footballClub.setRegion(generateRandomRegion());
        return footballClub;
    }

    private static Club generateRandomClub() {
        switch (randomNumber(1, 7)) {
            case 1:
                return Club.DINAMO;
            case 2:
                return Club.KOLOS;
            case 3:
                return Club.MON;
            case 4:
                return Club.SK;
            case 5:
                return Club.SPARTAK;
            case 6:
                return Club.UKRAINA;
            case 7:
                return Club.ZSU;
            default:
                return Club.DINAMO;
        }
    }

    private static Region generateRandomRegion() {
        switch (randomNumber(1, 7)) {
            case 1:
                return Region.Lvivska;
            case 2:
                return Region.Cherkaska;
            case 3:
                return Region.Chernigivska;
            case 4:
                return Region.Kharkivska;
            case 5:
                return Region.Ternopilska;
            case 6:
                return Region.Kyivska;
            case 7:
                return Region.Ghitomerska;
            default:
                return Region.Kyiv;
        }
    }

    private static CardStatus generateRandomCardStatus() {
        switch (randomNumber(1, 2)) {
            case 1:
                return CardStatus.PRODUCED;
            case 2:
                return CardStatus.RECIEVED;
            default:
                return CardStatus.PRODUCED;
        }
    }

    private static Age generateRandomAge() {
        switch (randomNumber(1, 3)) {
            case 1:
                return Age.CADET;
            case 2:
                return Age.JUNIOR;
            case 3:
                return Age.SENIOR;
            default:
                return Age.JUNIOR;
        }
    }

    private static Style generateRandomStyle() {
        switch (randomNumber(1, 3)) {
            case 1:
                return Style.FS;
            case 2:
                return Style.FW;
            case 3:
                return Style.GR;
            default:
                return Style.FW;
        }
    }

    private static String generateRandomExpireYear() {
        switch (randomNumber(1, 5)) {
            case 1:
                return "2013";
            case 2:
                return "2014";
            case 3:
                return "2015";
            case 4:
                return "2016";
            case 5:
                return "2017";
            default:
                return "2015";
        }
    }


}
