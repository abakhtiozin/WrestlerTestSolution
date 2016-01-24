package main.java.model;

public enum Style {
    FS("1"),
    FW("2"),
    GR("3");
    private String name;

    Style(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
