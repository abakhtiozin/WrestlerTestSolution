package main.java.model;

public enum CardStatus {
    PRODUCED("Produced", "1"),
    RECIEVED("Recieved", "2");

    private String name;
    private String id;

    CardStatus(String name, String id) {

        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
