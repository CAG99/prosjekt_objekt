package project;

public interface BilOppsett { // Grensesnitt for appen som Bil.java implementerer
    String getModell();
    String getFarge();
    String getFelger();

    void setModell(String modell);
    void setFarge(String farge);
    void setFelger(String felger);
}