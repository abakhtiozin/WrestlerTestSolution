package main.java.model;

public enum Region {

    AR_Krym("AR Krym","1"),
    Vynnitska("Vynnitska","2"),
    Dnipropetrovska("Dnipropetrovska","3"),
    Donetska("Donetska","4"),
    Ghitomerska("Ghitomerska","5"),
    Zakarpatska("Zakarpatska","6"),
    Zaporizka("Zaporizka","7"),
    Ivano_Frankivska("Ivano-Frankivska","8"),
    Kyivska("Kyivska","9"),
    Kyrovogradska("Kyrovogradska","10"),
    Luganska("Luganska","11"),
    Lvivska("Lvivska","12"),
    Mykolaivska("Mykolaivska","13"),
    Odeska("Odeska","14"),
    Poltavska("Poltavska","15"),
    Rivnenska("Rivnenska","16"),
    Sumska("Sumska","17"),
    Ternopilska("Ternopilska","18"),
    Kharkivska("Kharkivska","19"),
    Khersonska("Khersonska","20"),
    Khemlnicka("Khemlnicka","21"),
    Cherkaska("Cherkaska","22"),
    Chernivetska("Chernivetska","23"),
    Chernigivska("Chernigivska","24"),
    Kyiv("Kyiv","25"),
    Sevastopol("Sevastopol","26");

    private String name;

    public String getId() {
        return id;
    }

    private String id;

    Region(String name, String id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return this.name;
    }
}