package main.java.model;

public enum Club {
    DINAMO("Dinamo", "1"),
    KOLOS("Kolos", "2"),
    UKRAINA("Ukraina", "3"),
    SPARTAK("Spartak", "4"),
    MON("MON", "5"),
    ZSU("ZSU", "6"),
    SK("SK", "7");
    private String name;
    private String id;

    Club(String name, String id) {
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
