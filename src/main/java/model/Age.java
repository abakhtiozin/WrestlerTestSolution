package main.java.model;

public enum Age {
    CADET("Cadet","1"),
    JUNIOR("Junior","2"),
    SENIOR("Senior","3");

    private String name;
    private String id;

    Age(String name, String id) {
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
