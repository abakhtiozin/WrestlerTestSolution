package main.java.model;


public class Wrestler extends Person implements Comparable<Wrestler> {
    private FootballClub footballClub1;
    private FootballClub footballClub2;
    private Style style;
    private Age age;
    private String expireYear;
    private CardStatus cardStatus;

    public FootballClub getFootballClub1() {
        return footballClub1;
    }

    public void setFootballClub1(FootballClub footballClub1) {
        this.footballClub1 = footballClub1;
    }

    public FootballClub getFootballClub2() {
        return footballClub2;
    }

    public void setFootballClub2(FootballClub footballClub2) {
        this.footballClub2 = footballClub2;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public String getExpireYear() {
        return expireYear;
    }

    public void setExpireYear(String expireYear) {
        this.expireYear = expireYear;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Wrestler other = (Wrestler) obj;

        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;

        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;

        if (middleName == null) {
            if (other.middleName != null)
                return false;
        } else if (!middleName.equals(other.middleName))
            return false;

        if (dateOfBirth == null) {
            if (other.dateOfBirth != null)
                return false;
        } else if (!dateOfBirth.equals(other.dateOfBirth))
            return false;

        if (footballClub1 == null) {
            if (other.footballClub1 != null)
                return false;
        } else if (!footballClub1.equals(other.footballClub1))
            return false;

        if (footballClub1.getClub() == null) {
            if (other.footballClub1.getClub() != null)
                return false;
        } else if (!footballClub1.getClub().equals(other.footballClub1.getClub()))
            return false;

        if (footballClub1.getRegion() == null) {
            if (other.footballClub1.getRegion() != null)
                return false;
        } else if (!footballClub1.getRegion().equals(other.footballClub1.getRegion()))
            return false;

        if (footballClub1.getTrainer() == null) {
            if (other.footballClub1.getTrainer() != null)
                return false;
        } else if (!footballClub1.getTrainer().equals(other.footballClub1.getTrainer()))
            return false;

        if (footballClub2 == null) {
            if (other.footballClub2 != null)
                return false;
        } else if (!footballClub2.equals(other.footballClub2))
            return false;

        if (footballClub2.getTrainer() == null) {
            if (other.footballClub2.getTrainer() != null)
                return false;
        } else if (!footballClub2.getTrainer().equals(other.footballClub2.getTrainer()))
            return false;

        if (footballClub2.getRegion() == null) {
            if (other.footballClub2.getRegion() != null)
                return false;
        } else if (!footballClub2.getRegion().equals(other.footballClub2.getRegion()))
            return false;

        if (footballClub2.getClub() == null) {
            if (other.footballClub2.getClub() != null)
                return false;
        } else if (!footballClub2.getClub().equals(other.footballClub2.getClub()))
            return false;

        if (style == null) {
            if (other.style != null)
                return false;
        } else if (!style.equals(other.style))
            return false;

        if (age == null) {
            if (other.age != null)
                return false;
        } else if (!age.equals(other.age))
            return false;

        if (expireYear == null) {
            if (other.expireYear != null)
                return false;
        } else if (!expireYear.equals(other.expireYear))
            return false;

        if (cardStatus == null) {
            if (other.cardStatus != null)
                return false;
        } else if (!cardStatus.equals(other.cardStatus))
            return false;

        return true;
    }

    @Override
    public int compareTo(Wrestler other) {
        return this.lastName.compareTo(other.lastName);
    }
}
